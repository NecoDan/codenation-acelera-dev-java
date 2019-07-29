package br.com.codenation.acelera.dev.java.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MultipartUtility {

	private String boundary;
	private String LINE_FEED = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream outputStream;
	private PrintWriter writer;

	public MultipartUtility() {
	}

	public MultipartUtility(String requestURL, String charset) {
		try {
			this.inicializar(requestURL, charset);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void inicializar(String requestURL, String charset) throws IOException {
		this.charset = charset;

		// cria um limite exclusivo com base no registro de data e hora
		this.boundary = "===" + System.currentTimeMillis() + "===";

		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true); // indicates POST method
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		httpConn.setRequestProperty("User-Agent", "Daniel Agent");
		outputStream = httpConn.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
	}

	public void addFormField(String name, String value) {
		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE_FEED);
		writer.append("Content-Type: text/plain; charset=" + charset).append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.append(value).append(LINE_FEED);
		writer.flush();
	}

	public void addFilePart(String fieldName, File uploadFile) throws IOException {
		String fileName = uploadFile.getName();

		writer.append("--" + boundary).append(LINE_FEED);
		writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"")
				.append(LINE_FEED);
		writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);

		writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
		writer.append(LINE_FEED);
		writer.flush();

		FileInputStream inputStream = new FileInputStream(uploadFile);
		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inputStream.read(buffer)) != -1)
			outputStream.write(buffer, 0, bytesRead);

		outputStream.flush();
		inputStream.close();

		writer.append(LINE_FEED);
		writer.flush();
	}

	public void addHeaderField(String name, String value) {
		writer.append(name + ": " + value).append(LINE_FEED);
		writer.flush();
	}

	/**
	 * Conclui a solicitação e recebe resposta do servidor.
	 * 
	 * @return uma lista de Strings como resposta no caso do servidor retornar
	 *         status OK, caso contrário, uma exceção é lançada.
	 * @throws IOException      
	 */
	public List<String> finalizar() throws IOException {
		List<String> respostas = new ArrayList<String>();

		writer.append(LINE_FEED).flush();
		writer.append("--" + boundary + "--").append(LINE_FEED);
		writer.close();

		// Verifica o código de status do servidor primeiro
		int status = httpConn.getResponseCode();

		if (status == HttpURLConnection.HTTP_OK) {
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
			String linha = null;

			while ((linha = bufferReader.readLine()) != null)
				respostas.add(linha);

			bufferReader.close();
			httpConn.disconnect();
		} else {
			throw new IOException("Server returned non-OK status: " + status);
		}

		return respostas;
	}	
	
	public Boolean enviarNovo(String urlService, File file, String path) throws ClientProtocolException, IOException {
		Boolean enviado = Boolean.FALSE;
		CloseableHttpClient httpClients = HttpClients.createDefault();

		if (urlService == null || (urlService != null && urlService.isEmpty()))
			return enviado;

		if (file == null && (path != null && !path.isEmpty()))
			file = new File(path);

		System.out.println("Nome do arquivo: " + file.getName());

		try {
			HttpPost httpPostMan = new HttpPost(urlService);

			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addBinaryBody("answer", file, ContentType.MULTIPART_FORM_DATA, "file")
					.setCharset(Charset.defaultCharset()).build();

			httpPostMan.setEntity(reqEntity);
			System.out.println("Executando a requisição" + httpPostMan.getRequestLine());

			CloseableHttpResponse response = httpClients.execute(httpPostMan);
			
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity respostaEntity = response.getEntity();

				if (respostaEntity != null)
					System.out.println("O conteúdo da resposta contém o tamanho: " + respostaEntity.getContentLength());

				String msg = EntityUtils.toString(response.getEntity());

				if (response.getStatusLine().getStatusCode() != 200) {
					System.out.println(String.valueOf(response.getStatusLine().getStatusCode())
							+ " with cache wipe url " + urlService + "\n" + msg);
					throw new IOException(response.getStatusLine().getStatusCode() + " with cache wipe url "
							+ urlService + "\n" + msg);
				} else {
					System.out.println(String.valueOf(response.getStatusLine().getStatusCode())
							+ " with cache wipe url " + urlService + "\n" + msg);
				}

				EntityUtils.consume(respostaEntity);
			} finally {
				response.close();
				enviado = Boolean.TRUE;
			}
		} finally {
			httpClients.close();
			enviado = Boolean.TRUE;
		}

		return enviado;
	}
}
