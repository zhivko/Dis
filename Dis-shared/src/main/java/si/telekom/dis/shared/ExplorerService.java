package si.telekom.dis.shared;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
	 */
	@RemoteServiceRelativePath("explorer")
	public interface ExplorerService extends RemoteService {
		List<Document> getObjects(String folder, boolean current, String loginName, String password, int start, int length) throws ServerException;
		List<Action> getActionsForObject(String loginName, String password, String r_object_id) throws ServerException;
		List<String> getRenditions(String loginName, String password, String r_object_id) throws ServerException;
		ProfileAttributesAndValues getProfileAttributesAndValues(String loginName, String password, String r_object_id) throws ServerException;
		List<String[]> getValuesFromDql(String loginName, String password,  String likeString, String dql) throws ServerException;
		List<String[]> getValuesFromSql(String loginName, String password,  String likeString, String jdbcStringAndSql) throws ServerException;
		List<String[]> getValuesFromRest(String loginName, String password,  String likeString, String restUrl) throws ServerException;
		
		List<List<String>> getDefaultValueDql(String dql) throws ServerException;
		
		Void setAttributes(String loginName, String password, String r_object_id, List<String[]> values) throws ServerException;
		Void deleteObject(String loginName, String password, String r_object_id, boolean allVersions) throws ServerException;
		Void deleteObjects(String loginName, String password, List<String> r_object_ids, boolean allVersions) throws ServerException;
		List<List<String>> dqlLookup(String loginName, String password, String dql) throws ServerException;
		Void newFolder(String loginName, String password, String folderName, String pareFolderPath) throws ServerException;
		Void setUsersForRoles(String loginName, String password, List<String> r_object_id, Map<String,List<String>> rolesUsers) throws ServerException;
		
		Void checkout(String loginName, String password, String r_object_id) throws ServerException;
		Void cancelCheckout(String loginName, String password, String r_object_id) throws ServerException;
		Void unlock(String loginName, String password, String r_object_id) throws ServerException;
	
		List<Document> versions(String loginName, String password, String r_object_id) throws ServerException;
		Document promote(String loginName, String password, String r_object_id) throws ServerException;
		Document demote(String loginName, String password, String r_object_id) throws ServerException;
		
		List<List<String>> auditTrail(String loginName, String password, String r_object_id, String eventFilter, int start, int length) throws ServerException;
		Void addVersionLabel(String loginName, String password, List<String> r_object_id, String labelVersion) throws ServerException;
		Void removeVersionLabel(String loginName, String password, List<String> r_object_id, String labelVersion) throws ServerException;
		
		Document newDocument(String loginName, String password, String profileId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers, String rObjectIdOfObjectOrFolder) throws ServerException;
		Document importDocument(String loginName, String password, String objectId, String profileId, String stateId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers, byte[] contentBase64, String format) throws ServerException;
		Document classifyDocument(String loginName, String password, String r_object_id, String profileId, String stateId, Map<String,List<String>> attributes, Map<String,List<String>> rolesUsers) throws ServerException;
		
		List<Document> runSearchQuery(String loginName, String password, String dql, MyParametrizedQuery pQuery, int start, int length) throws ServerException;
		List<List<String>> showPdfTags(String loginName, String loginPassword, String r_object_id) throws ServerException;
		
		Void updateBusinessNotification(String loginName, String password, String r_object_id) throws ServerException;
		
		Void newRelease(String loginName, String password, String r_object_id) throws ServerException;
		
		List<String> recognizeFormat(String base64Content) throws ServerException;
		
		Void removeRendition(String loginName, String password, String r_object_id, String format) throws ServerException;
		
		String massDownloadContent(String loginName, String password, List<String> r_object_ids) throws ServerException;

		Void prepareAiTrainDataForFolderAndClassification(String loginName, String password, List<String> r_object_ids, String classification, boolean deleteTrainingDir) throws ServerException;
		
		Void syncERenderTemplate(String loginName, String password, String r_object_id) throws ServerException;
		
		Void configureERender(String loginName, String password, String xml) throws ServerException;
	}
