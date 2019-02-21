# security-sdk API Doc

## Users

### queryUsers
since security-keycloak-sdk 1.0.0

```

	/**
	 * 查询用户列表
	 * 
	 * @param queryParams
	 *            可用于查询的参数，类型Map，key分别为：userId[用户ID] userName[用户名]
	 * @return
	 */
	public List queryUsers(Map queryParams);
```

调用示例
```

	IUserProvider user = SDKFactory.getUserProviderImpl();
	Map param = new HashMap();
	param.put("userId", params.get("userId"));
	param.put("userName", params.get("userName"));
	List users = user.queryUsers(param);
```
	
返回示例
```

	[
	    {
	        "id": "ada61d11-e87b-4c8d-925f-af8fb6833612",
	        "createdTimestamp": 1526953828876,
	        "username": "dev",
	        "enabled": true,
	        "firstName": "开发者",
	        "attributes": {
	            "LDAP_ENTRY_DN": [
	                "krbPrincipalName=dev-tenant@INDATA.COM,cn=INDATA.COM,cn=kerberos,dc=indata,dc=com"
	            ]
	        }
	    },
	    {
	        "id": "59cc2847-e0dc-4ea4-8f85-a12f629ea6af",
	        "createdTimestamp": 1526953828891,
	        "username": "xiaoc",
	        "enabled": true,
	        "firstName": "xiaocname",
	        "attributes": {
	            "LDAP_ENTRY_DN": [
	                "krbPrincipalName=xiaoc-tenant@INDATA.COM,cn=INDATA.COM,cn=kerberos,dc=indata,dc=com"
	            ]
	        }
	    }
	]
```
