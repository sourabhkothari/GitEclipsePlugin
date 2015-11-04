package com.psl.git.helper;

import com.google.gson.Gson;
import com.psl.git.model.User;
import com.psl.git.util.GitConstants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

public class GitHelper {

	Gson gson = new Gson();

	public User getGitFiles(String path, String userName, String password,
			boolean isRoot) {
		System.out.println("GitHelper.getGitFiles");
		User user = null;
		WebResource webResource;
		try {
			Client client = Client.create();
			Form form = new Form();
			form.add("username", userName);
			form.add("password", password);
			form.add("url", path);
			if (isRoot) {
				webResource = client
						.resource(GitConstants.REST_GIT_VALIDATION_URL);
			} else {
				webResource = client
						.resource(GitConstants.REST_GIT_GETTREE_URL);
			}
			ClientResponse response = webResource.accept("application/json")
					.post(ClientResponse.class, form);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			user = gson.fromJson(response.getEntity(String.class), User.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return user;
	}

}
