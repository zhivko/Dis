//https://github.com/swagger-api/swagger-core/wiki/Swagger-Core-Jersey-2.X-Project-Setup-1.5
package si.telekom.dis.server.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfVersionPolicy;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.client.IDfVersionPolicy;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.common.IDfId;

import io.swagger.annotations.Api;
import si.telekom.dis.server.AdminServiceImpl;
import si.telekom.dis.server.ExplorerServiceImpl;
import si.telekom.dis.server.UploadServlet;
import si.telekom.dis.server.rest.UpdateDocumentRequest.VersionEnum;
import si.telekom.dis.server.rest.api.NotFoundException;
import si.telekom.dis.server.rest.api.ProfilesApiService;
import si.telekom.dis.server.restCommon.User;
import si.telekom.dis.shared.AttributeRoleStateWizards;
import si.telekom.dis.shared.AttributeValue;
import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.State;
import si.telekom.dis.shared.UserGroup;

// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/disRest/dqlLookup?loginName=zivkovick&passwordEncrypted=RG9pdG1hbjc4OTAxMg==&dql=select%20*%20from%20dm_cabinet
// http://localhost:8080/Dis-server/rest/application.wadl?detail=true
// http://localhost:8080/Dis-server/rest/disRest/importDocument
// https://erender-test.ts.telekom.si:8445/Dis/rest/disRest/importDocument
// mvn clean package -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true

// https://localhost:8445/Dis-server/rest/swagger/getListingYaml
// https://localhost:8445/Dis-server/rest/swagger/getListingJson
// https://localhost:8445/Dis-server/rest/application.wadl
// https://localhost:8445/Dis-server/api/swagger?type=yaml
// https://localhost:8445/Dis-server/api/swagger?type=json
@Api
public class DisProfileServiceRest extends ProfilesApiService {
	public DisProfileServiceRest() {
		super();
	}

	@Override
	public Response getProfileAttributes(String profileId, String xTransactionId, String wizard, String roleId, String stateId,
			SecurityContext securityContext) throws NotFoundException {
		User user = (User) securityContext.getUserPrincipal();
		String loginName = user.getId();
		String password = user.getPaswordBase64Encoded();

		try {
			ProfileAttributesResponse ret = new ProfileAttributesResponse();

			Profile prof = AdminServiceImpl.getInstance().profiles.get(profileId);
			if (prof == null) {
				String allPids = new String();
				for (String pid : AdminServiceImpl.getInstance().profiles.keySet()) {
					allPids = allPids + "," + pid;
				}
				if (allPids.endsWith(","))
					allPids = allPids.substring(0, allPids.length() - 1);

				throw new Exception("no such profile: " + profileId + " possible profiles: " + allPids);
			}

			for (AttributeRoleStateWizards attsRSW : prof.attributeRolesStatesWizards) {
				boolean found = false;
				if (wizard != null) {
					for (String wizzardType : attsRSW.wizards) {
						if (wizzardType.equals(wizard)) {
							found = true;
						}
					}
				} else {
					for (String tmpRoleId : attsRSW.stateRole.get(stateId)) {
						if (roleId.equals(tmpRoleId)) {
							found = true;
						}
					}
				}

				if (found) {
					for (si.telekom.dis.shared.Attribute att : attsRSW.attributes) {
						ProfileAttribute pa = new ProfileAttribute();
						pa.setName(att.dcmtAttName);
						pa.setMandatory(att.isMandatory);
						if (att.dqlValueListDefinition != null)
							pa.setValues(att.dqlValueListDefinition);
						if (att.jdbcValueListDefinition != null)
							pa.setValues(att.jdbcValueListDefinition);
						if (att.commaSeparatedValueListDefinition != null)
							pa.setValues(att.commaSeparatedValueListDefinition);
						ret.addAttributesItem(pa);
					}
					break;
				}
			}

			return Response.ok(ret).build();
		} catch (Exception ex) {
			Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
		}
		return null;

	}

}
