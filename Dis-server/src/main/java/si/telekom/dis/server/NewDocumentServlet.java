package si.telekom.dis.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.documentum.fc.client.IDfFormat;
import com.documentum.fc.client.IDfPersistentObject;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfId;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import si.telekom.dis.shared.ServerException;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
@RemoteServiceRelativePath("newDocument")
public class NewDocumentServlet extends RemoteServiceServlet {

//	@Override
//	public void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String loginName = req.getParameter("loginName");
//		String loginPassword = req.getParameter("loginPassword");
//		String rObjectId = req.getParameter("r_object_id");
//		String rendition = req.getParameter("rendition");
//		
//		IDfSession userSession = null;
//		ByteArrayOutputStream baos = null;
//		
//		boolean foundContent=false;
//		String mimeType="";
//		String fileName="";
//		String dosExtension="";
//		
//		try {
//			baos = new ByteArrayOutputStream();
//			if (loginName == null)
//				throw new Exception("LoginName should not be null");
//			if (loginPassword == null)
//				throw new Exception("Password should not be null");
//
//			userSession = AdminServiceImpl.getSession(loginName, loginPassword);
//			IDfPersistentObject persObj = userSession.getObject(new DfId(rObjectId));
//			IDfSysObject sysObj = (IDfSysObject)persObj;
//			IDfFormat format = userSession.getFormat(rendition);
//			
//			
//			mimeType = format.getMIMEType().toString();
//			fileName = sysObj.getObjectName();
//			dosExtension = format.getDOSExtension();
//			
//			baos.write(read(sysObj.getContentEx(rendition, 0)));	
//			foundContent = true;
//		} catch (Exception ex) {
//			new ServerException(ex);
//		} finally {
//			try {
//				if (userSession != null)
//					userSession.getSessionManager().release(userSession);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//
//		
//		resp.setHeader("Expires", "0");
//		resp.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
//		resp.setHeader("Pragma", "public");
//		if (foundContent) {
//			resp.setHeader("fileName", fileName + "." + dosExtension);
//			resp.setContentType(mimeType);
//			resp.setHeader("Content-disposition", "inline; filename=content." + dosExtension);
//			resp.setContentLength(baos.size());
//			resp.setContentType(mimeType);
//			resp.setStatus(200);
//		} else {
//			resp.setStatus(404);
//			resp.setContentType("text/html; charset=UTF-8");
//			baos.write("\nRačuna s to številko ni bilo možno najti.".getBytes("UTF-8"));
//		}		
//		
//		ServletOutputStream out = resp.getOutputStream();
//		baos.writeTo(out);
//		out.flush();		
//		
//	}
//	
//	
//	public byte[] read(ByteArrayInputStream bais) throws IOException {
//		byte[] array = new byte[bais.available()];
//		bais.read(array);
//
//		return array;
//	}	
	
}
