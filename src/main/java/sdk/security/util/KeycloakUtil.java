package sdk.security.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import sdk.security.filter.HttpServletThreadLocal;

public class KeycloakUtil {

	public static KeycloakSecurityContext getKeycloakSecurityContext() {
		HttpServletRequest request = HttpServletThreadLocal.getRequest();
		RefreshableKeycloakSecurityContext context = (RefreshableKeycloakSecurityContext) request
				.getAttribute(KeycloakSecurityContext.class.getName());
		return context;
	}

	public static AccessToken getAccessToken() {
		return getKeycloakSecurityContext().getToken();
	}

	public static String getRealm() {
		return getKeycloakSecurityContext().getRealm();

	}
	public static String getSecurityContextUrl(){
		String authServerUrl = "";
		AccessToken token = KeycloakUtil.getAccessToken();
		String issuer = token.getIssuer();
		try {
			URL url = new URL(issuer);
			authServerUrl = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/auth";
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return authServerUrl;
	}
}
