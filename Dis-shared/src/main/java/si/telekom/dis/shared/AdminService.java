package si.telekom.dis.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("admin")
public interface AdminService extends RemoteService {
	List<Action> getActions(String loginName, String password) throws ServerException;

	Action getActionById(String actionId) throws ServerException;	
	
	List<DocType> getDocTypes(String loginName, String password) throws ServerException;
	DocType getDocType(String loginName, String password, String doctypeName) throws ServerException;
	

	List<Profile> getProfiles(String loginName, String password) throws ServerException;

	Profile getProfile(String loginName, String password, String profileId) throws ServerException;

	String setProfile(String loginName, String password, Profile prof) throws ServerException;

	void deleteProfile(String loginName, String password, String profileId) throws ServerException;

	List<Profile> getProfilesForClassSign(String loginName, String password, String classSign, String wizardType) throws ServerException;

	void syncDoctypes(String loginName, String password) throws ServerException;

	void registerTable(String loginName, String password, String tableName) throws ServerException;

	List<List<String>> getRegTableValues(String loginName, String loginPass, String regTableId, int start, int length) throws ServerException;

	List<String> getRegTables(String loginName, String loginPass) throws ServerException;

	List<String> getRegTableFieldNames(String loginName, String loginPass, String regTableName) throws ServerException;

	void updateRegTableRow(String loginName, String loginPass, String regTableId, String value, int columnIndex, String pkValue) throws ServerException;

	List<MyParametrizedQuery> getSearchQueries(String loginName, String dctmUserName, String loginPass) throws ServerException;
	
	List<MyParametrizedQuery> getLazySearchQueries(String loginName, String dctmUserName, String loginPass) throws ServerException;
	
	MyParametrizedQuery getParametrizedQueryByName(String loginName, String loginPass, String name) throws ServerException;

	void editParametrizedQuery(String loginName, String loginPass, String oldName, String newName, String newDql, List<String> groups, List<String> orderBy, List<String> orderBydirections, String filterClass) throws ServerException;

	String duplicateParametrizedQuery(String loginName, String loginPass, String name) throws ServerException;
	
	DocType getDocTypeFromDql(String loginName, String loginPass, String dql) throws ServerException;
	
	List<List<String>> getColIdsForTemplate(int templateId, int rowNo, int pageLength) throws ServerException;

	void updateCollId(String loginName, String loginPass, int templateId, String templateName, String col_id, String columnName, String value) throws ServerException;	

	void deleteCollId(String loginName, String loginPass, int templateId, String col_id) throws ServerException;	

	void transferToAllEnvironments(String loginName, String loginPass, int templateId) throws ServerException;
	
}
