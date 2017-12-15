package sdk.security.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.AccessToken;

import sdk.security.filter.HttpServletThreadLocal;

public class KeycloakUtil {

	private static final String logout = "/protocol/openid-connect/logout?redirect_uri=";

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

	public static String getSecurityContextUrl() {
		String authServerUrl = "";
		AccessToken token = getAccessToken();
		String issuer = token.getIssuer();
		try {
			URL url = new URL(issuer);
			authServerUrl = url.getProtocol() + "://" + url.getHost() + ":" + url.getPort() + "/auth";

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return authServerUrl;
	}

	public static String getLogoutUrl(String redirectUrl) {
		String logoutUrl = "";
		if (!"".equals(redirectUrl) && redirectUrl != null) {
			logoutUrl = getAccessToken().getIssuer() + logout;
			if (redirectUrl.startsWith("http") || redirectUrl.startsWith("https")) {
				logoutUrl += redirectUrl;
			} else {
				try {
					HttpServletRequest request = HttpServletThreadLocal.getRequest();
					String contexPath = request.getContextPath();
					URL url = new URL(request.getRequestURL().toString());
					String protocol = url.getProtocol();
					String host = url.getHost();
					int port = url.getPort();
					logoutUrl += protocol + "://" + host;
					if (port > 0) {
						logoutUrl += ":" + port;
					}
					if (redirectUrl.startsWith("/")) {
						logoutUrl += contexPath + redirectUrl;
					} else {
						logoutUrl += contexPath + "/" + redirectUrl;
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}

		}
		return logoutUrl;
	}
}
