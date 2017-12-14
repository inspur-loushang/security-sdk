package sdk.security.authz;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class SDKPermitButton extends TagSupport {
	private static final long serialVersionUID = 1L;
	private String operationCode;

	public void setoperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public int doStartTag() throws JspException {

		try {
			if ((this.operationCode != null) && (!("".equals(this.operationCode)))) {

				Boolean codeIsExist = AuthorizationProvider.hasPermission(operationCode);
				if (codeIsExist) {
					this.pageContext.getOut().print("");
				} else {
					this.pageContext.getOut().print(" display:none ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}