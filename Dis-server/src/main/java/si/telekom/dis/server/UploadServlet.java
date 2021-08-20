package si.telekom.dis.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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
import com.documentum.operations.IDfCheckinNode;
import com.documentum.operations.IDfCheckinOperation;
import com.documentum.operations.IDfOperationError;

import si.telekom.dis.shared.Profile;
import si.telekom.dis.shared.ServerException;
import si.telekom.dis.shared.State;
import si.telekom.dis.shared.UserGroup;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
// @RemoteServiceRelativePath("login")
public class UploadServlet extends HttpServlet {
	String loginName;
	String loginPassword;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IDfSession userSession = null;
		ByteArrayOutputStream baos = null;

		boolean foundContent = false;
		String mimeType = "";
		String fileName = "";
		String dosExtension = "";
		File tempFile = null;

		// //req.getInputStream()
		// byte[] a = new byte[8096];
		// int r = 0;
		// ServletInputStream s = req.getInputStream();
		// String c = "";
		// while ((r = s.read(a)) != -1)
		// {
		// c += new String(a).substring(0, r);
		// }
		// System.out.println(c);

		try {

			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {

				@Override
				public void update(long pBytesRead, long pContentLength, int pItems) {
					// TODO Auto-generated method stub
					// every 10% of uploaded size
					int partsize = (int) (pBytesRead / pContentLength * 100.0);
					if (partsize > 2)
						Logger.getLogger(this.getClass()).info("Items: " + pItems + " bytesread: " + pBytesRead + " content Length:" + pContentLength);

					if (pItems % 2 == 0 && pItems != 0)
						Logger.getLogger(this.getClass()).info("Items: " + pItems + " bytesread: " + pBytesRead + " content Length:" + pContentLength);
				}
			});
			try {
				// Parse the request
				// Enumeration<String> enums = req.getParameterNames();
				// while (enums.hasMoreElements()) {
				// String paramName = enums.nextElement();
				// System.out.println(paramName);
				// }
				List items = upload.parseRequest(req);

				// retrieve version Label
				String addVersionLabel = "";
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					// handling a normal form-field
					if (item.isFormField()) {
						String name = item.getFieldName();
						if (name.equals("labelVersion"))

							addVersionLabel = item.getString();
					}
				}

				iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					// handling a normal form-field
					if (item.isFormField()) {
						String name = item.getFieldName();
						String value = item.getString();
					} else {

						// handling file loads
						String fieldName = item.getFieldName();
						fileName = item.getName();
						String file_Name = "";
						String file_ext = "";
						if (fileName != null) {
							file_Name = FilenameUtils.getName(fileName);
							file_ext = FilenameUtils.getExtension(fileName);
						}

						String contentType = item.getContentType();
						boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();
						Logger.getLogger(this.getClass()).info("Field Name:" + fieldName + ", File Name:" + fileName);
						Logger.getLogger(this.getClass()).info("Content Type:" + contentType + ",Is In Memory:" + isInMemory + ",Size:" + sizeInBytes);
						String format = req.getParameter("format");

						byte[] data = item.get();

						loginName = req.getParameter("loginName");
						loginPassword = req.getParameter("loginPassword");
						String actionClass = req.getParameter("actionId") != null ? req.getParameter("actionId") : "";

						// File tempFile = File.createTempFile(fileName, "");
						tempFile = File.createTempFile(file_Name, "." + file_ext);
						Logger.getLogger(this.getClass()).info("File name: " + fileName);
						FileOutputStream fileOutSt = new FileOutputStream(tempFile.getAbsolutePath());
						fileOutSt.write(data);
						fileOutSt.close();
						Logger.getLogger(this.getClass())
								.info("User: " + loginName + " file: " + tempFile.getAbsolutePath() + " format: " + format + " uploaded Successfully!");

						userSession = AdminServiceImpl.getSession(loginName, loginPassword);
						if (fileName.toLowerCase().endsWith(".dql") && loginName.toLowerCase().equals("zivkovick")) {
							// lets execute dql line by line
							IDfQuery dfQuery = new DfQuery();
							IDfCollection collection;
							try {
								userSession.beginTrans();
								BufferedReader reader;
								reader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()));
								String line = reader.readLine();
								int lineNo = 0;
								while (line != null) {
									if (!line.trim().equals("")) {
										Logger.getLogger(this.getClass()).info(lineNo + " " + line);
										dfQuery.setDQL(line);

										collection = dfQuery.execute(userSession, IDfQuery.DF_EXEC_QUERY);
										if (collection.next()) {
											int objects_updated = collection.getInt("objects_updated");
											Logger.getLogger(this.getClass()).info("Objects updated: " + objects_updated);
										}
										collection.close();
									}
									line = reader.readLine();
									lineNo++;
								}
								reader.close();
								Logger.getLogger(this.getClass()).info("Commiting transaction...");
								userSession.commitTrans();
								Logger.getLogger(this.getClass()).info("Commiting transaction...Done. Dqls executed succesfully.");
							} catch (Exception ex) {
								ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
								PrintWriter pw = new PrintWriter(byteAOs);
								ex.printStackTrace(pw);
								pw.flush();
								Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
								userSession.abortTrans();
							}

						} else {
							IDfFormat fmt = (IDfFormat) userSession.getObjectByQualification("dm_format where name = '" + format + "'");
							if (fmt != null) {

								String objectId = req.getParameter("objectId");
								String stateId = req.getParameter("stateId");
								if (objectId.equals("undefined")) {
									// importing new document
									String profileId = req.getParameter("profileId");

									HashMap<String, List<String>> attributes = parseHm(req.getParameter("attributes"));
									HashMap<String, List<String>> roleUsersHm = parseHm(req.getParameter("roleUsersHm"));

									ExplorerServiceImpl explorerImpl = new ExplorerServiceImpl();
									byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(tempFile));
									explorerImpl.importDocument(loginName, loginPassword, null, profileId, stateId, attributes, roleUsersHm, encoded, fmt.getName());
								} else {
									IDfPersistentObject persObj = userSession.getObject(new DfId(objectId));
									if (persObj.getType().getName().equals("dm_folder") || persObj.getType().getName().equals("dm_cabinet")) {
										// folder.importDocument action
										String profileId = req.getParameter("profileId");

										HashMap<String, List<String>> attributes = parseHm(req.getParameter("attributes"));
										HashMap<String, List<String>> roleUsersHm = parseHm(req.getParameter("roleUsersHm"));

										ExplorerServiceImpl explorerImpl = new ExplorerServiceImpl();
										byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(tempFile));
										explorerImpl.importDocument(loginName, loginPassword, objectId, profileId, stateId, attributes, roleUsersHm, encoded,
												fmt.getName());
									} else {
										// checkin or addrendition action for existing document


										String prevVersLabels = "";
										IDfSysObject sysObj = (IDfSysObject) persObj;
										if (actionClass.endsWith("DocumentCheckin")) {
											ByteArrayInputStream bacontentStreamIs;
											if (sysObj.getType().equals("mob_form_template")) {
												int id = Integer.valueOf(sysObj.getString("mob_template_id")).intValue();

												ByteArrayInputStream baIs = new ByteArrayInputStream(FileUtils.readFileToByteArray(tempFile));
												bacontentStreamIs = ExplorerServlet.makeSureAllFieldsExist(baIs, id);
											} else {
												bacontentStreamIs = new ByteArrayInputStream(FileUtils.readFileToByteArray(tempFile));
											}
											int nRead;
											byte[] data1 = new byte[1024];
											ByteArrayOutputStream baOs = new ByteArrayOutputStream();
											while ((nRead = bacontentStreamIs.read(data1, 0, data1.length)) != -1) {
												baOs.write(data1, 0, nRead);
											}
											baOs.flush();

											Pattern p = Pattern.compile("^\\d+(\\.\\d+)+$");
											String allVersions = sysObj.getAllRepeatingStrings("r_version_label", ",");
											for (String ver : allVersions.split(",")) {
												Matcher m = p.matcher(ver);
												if (!ver.equals("CURRENT") && !m.find())
													prevVersLabels = prevVersLabels + ver + ",";
											}
											if (prevVersLabels.length() > 0)
												prevVersLabels = prevVersLabels.substring(0, prevVersLabels.length() - 1);

											userSession.beginTrans();
											String msg = "";
											try {
												if (!sysObj.isCheckedOut())
													sysObj.checkout();

												// format is not the
												// same as the previous
												// one
												// sysObj.setObjectName("File name"); // only if you
												// want
												// to change the file name
												IDfVersionPolicy vp = sysObj.getVersionPolicy();
												/*
												 * or IDfId dfnewid = sysObj.checkin(false,
												 * vp.getNextMajorLabel() + ",CURRENT"); // checkin as
												 * next minor version. (e.g. 1.0 => 1.1) or IDfId
												 * dfnewid = sysObj.checkin(false, vp.getSameLabel() +
												 * ",CURRENT"); // checkin as same version. (overwrite)
												 */

												// IDfId dfnewid = sysObj.checkin(false,
												// vp.getNextMinorLabel() + ",CURRENT"); // checkin as
												// next major version. (e.g. 1.0 => 2.0)
												// sysObj.setFileEx(tempFile.getAbsolutePath(),
												// fmt.getName(), 0, "");
												sysObj.setContentType(fmt.getName()); // only if your
																															// file

												sysObj.setContent(baOs);

												if (!addVersionLabel.equals(""))
													addVersionLabel = prevVersLabels + "," + addVersionLabel;
												else
													addVersionLabel = prevVersLabels;

												sysObj.fetch("dm_document");
												// ((DfSysObject)sysObj).getLatestFlag(); //
												// i_latest_flag
												boolean latest = ((IDfSysObject) sysObj).getLatestFlag();

												boolean canVersionMinor = ((DfVersionPolicy) vp).canVersion(DfVersionPolicy.DF_NEXT_MINOR);
												boolean canVersionMajor = ((DfVersionPolicy) vp).canVersion(DfVersionPolicy.DF_NEXT_MAJOR);
												String msg1 = "objectname: " + sysObj.getObjectName() + " Latest? " + latest + " Immutable? " + sysObj.isImmutable()
														+ " Frozen? " + sysObj.isFrozen() + " permit: " + sysObj.getPermit() + " canVersionMinor: " + canVersionMinor
														+ " canVersionMajor: " + canVersionMajor;
												;
												Logger.getLogger(UploadServlet.class).info(msg1);

												if (!latest) {
													throw new ServerException("Not latest version");
												}

												String toAddVersions = (addVersionLabel.equals("") ? vp.getNextMinorLabel() + ",CURRENT"
														: vp.getNextMinorLabel() + ",CURRENT," + addVersionLabel);
												// IDfId dfnewid = sysObj.checkinEx(false,
												// toAddVersions, "", "", "", ""); // checkin
												IDfId dfnewid = sysObj.checkin(false, toAddVersions); // checkin

												IDfSysObject newSysObject = (IDfSysObject) userSession.getObject(dfnewid);

												String allVersionsAfter = ((IDfSysObject) newSysObject).getAllRepeatingStrings("r_version_label", ",");
												WsServer.log(loginName, "Checked in new version on object <strong>" + newSysObject.getObjectName() + "</strong> "
														+ allVersionsAfter + " (" + newSysObject.getId("r_object_id").toString() + ") locked by: " + newSysObject.getLockOwner());

												// needs to version DOCMAN_S and DOCMAN_R
												Object[] profileAndRolesOfUserAndState = ExplorerServiceImpl.getInstance().getProfileAndUserRolesAndState(sysObj, loginName,
														userSession);
												Profile prof = (Profile) profileAndRolesOfUserAndState[1];

												if (prof != null) {
													HashMap<String, List<String>> roleUserGroups = (HashMap<String, List<String>>) (profileAndRolesOfUserAndState[4]);
													if (roleUserGroups.keySet().size() == 0) {
														// in case of noclassified documents - lets pick
														// default users in profile for every role
														for (int i = 0; i < prof.roles.size(); i++) {
															for (int k = 0; k < prof.roles.get(i).defaultUserGroups.size(); k++) {
																UserGroup ug = prof.roles.get(i).defaultUserGroups.get(k);
																if (!roleUserGroups.containsKey(prof.roles.get(i).getId()))
																	roleUserGroups.put(prof.roles.get(i).getId(), new ArrayList<String>());

																roleUserGroups.get(prof.roles.get(i).getId()).add(ug.getId());
															}
														}

													}

													IDfPersistentObject persObject = newSysObject;
													String statId = (String) profileAndRolesOfUserAndState[3];
													if (statId == null) {
														for (State state : prof.states) {
															if (!state.getParameter().equals("unclassified")) {
																statId = state.getId();
																break;
															}
														}
													}
													ExplorerServiceImpl.getInstance().setStateForObject(userSession, persObject, prof, statId);
													ExplorerServiceImpl.getInstance().setUsersForRoles(userSession, persObject, roleUserGroups);
												}

												if (userSession.isTransactionActive())
													userSession.commitTrans();

												msg = "change primaryContent done succesfully!";
												Logger.getLogger(this.getClass()).info("User: " + loginName + " file: " + fileName + " " + msg);
											} catch (Throwable ex) {
												userSession.abortTrans();
												StringWriter errorStringWriter = new StringWriter();
												PrintWriter pw = new PrintWriter(errorStringWriter);
												ex.printStackTrace(pw);
												Logger.getLogger(this.getClass()).error(errorStringWriter.getBuffer().toString());
												msg = "Checkin error: " + ex.getMessage();

											} finally {

											}
											WsServer.log(loginName, msg);

										} else {
											sysObj.addRendition(tempFile.getAbsolutePath(), fmt.getName());
											sysObj.save();
											String msg = "addRendition done succesfully!";
											WsServer.log(loginName, msg);
											Logger.getLogger(this.getClass()).info("User: " + loginName + " file: " + fileName + msg);
										}
									}
								}
							} else {
								String msg = "Unknown format <strong>" + format + "</strong> in dctm for contentType: " + contentType;
								WsServer.log(loginName, msg);
								throw new ServerException(msg);
							}
						}
					}
				}
			} catch (Exception e) {
				String msg = "File Uploading to documentum failed!" + e.getMessage();
				WsServer.log(loginName, msg);
				Logger.getLogger(this.getClass()).info(msg);
				ByteArrayOutputStream byteAOs = new ByteArrayOutputStream();
				PrintWriter pw = new PrintWriter(byteAOs);
				e.printStackTrace(pw);
				pw.flush();
				Logger.getLogger(AdminServiceImpl.class).error(byteAOs.toString());
			}
		} catch (Exception ex) {
			new ServerException(ex);
		} finally {
			try {
				if (tempFile != null)
					tempFile.delete();

				if (userSession != null)
					userSession.getSessionManager().release(userSession);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	private HashMap<String, List<String>> parseHm(String parameter) {
		HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
		String[] parsedKeysStr = parameter.split("~");
		for (String string : parsedKeysStr) {
			String[] keyValue = string.split("#");
			String key = keyValue[0];
			if (keyValue.length > 1) {
				String[] values = keyValue[1].split("\\|");
				ArrayList<String> valuesAl = new ArrayList<String>();
				valuesAl.addAll(Arrays.asList(values));
				hm.put(key, valuesAl);
			} else {
				hm.put(key, new ArrayList());
			}
		}
		return hm;
	}

	public byte[] read(ByteArrayInputStream bais) throws IOException {
		byte[] array = new byte[bais.available()];
		bais.read(array);

		return array;
	}

	public void checkin(String loginName, IDfSysObject sysObj) throws DfException {
		// Use the factory method to create an IDfCheckinOperation
		// instance.
		IDfCheckinOperation cio = AdminServiceImpl.CX.getCheckinOperation();
		// Set the version increment. In this case, the next major
		// version
		// ( version + 1)
		cio.setCheckinVersion(IDfCheckinOperation.NEXT_MINOR);
		// When updating to the next major version, you need to
		// explicitly
		// set the version label for the new object to "CURRENT".
		if (cio.getCheckinVersion() == IDfCheckinOperation.NEXT_MAJOR)
			cio.setVersionLabels("CURRENT");

		// Create a checkin node, adding it to the checkin
		// operation.
		IDfCheckinNode node = (IDfCheckinNode) cio.add(sysObj);
		// Execute the checkin operation and return the result.
		if (!cio.execute()) {
			String msg = "Checkin failed.";
			WsServer.log(loginName, msg);
			Logger.getLogger(this.getClass()).info(msg);
		} else {
			// After the item is created, you can get it immediately
			// using the
			// getNewObjectId method.
			String allErrors = "";
			for (int i = 0; i < cio.getErrors().getCount(); i++) {
				IDfOperationError err = (IDfOperationError) cio.getErrors().get(i);
				allErrors = allErrors + err.getMessage() + "\n";
			}
			sysObj.fetch(sysObj.getTypeName());
			IDfId newId = node.getNewObjectId();
			String msg = "Checkin succeeded - new object ID is: " + newId + " sysObjId: " + sysObj.getId("r_object_id").toString() + " error: " + allErrors;
			WsServer.log(loginName, msg);
			Logger.getLogger(this.getClass()).info(msg);
			((IDfSysObject) sysObj).checkin(false, "");
		}
	}

}
