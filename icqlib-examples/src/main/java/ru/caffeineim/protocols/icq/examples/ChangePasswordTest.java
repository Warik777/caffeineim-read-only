/**
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package ru.caffeineim.protocols.icq.examples;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ru.caffeineim.protocols.icq.core.OscarConnection;
import ru.caffeineim.protocols.icq.exceptions.ConvertStringException;
import ru.caffeineim.protocols.icq.integration.OscarInterface;
import ru.caffeineim.protocols.icq.integration.events.LoginErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaAckEvent;
import ru.caffeineim.protocols.icq.integration.events.StatusEvent;
import ru.caffeineim.protocols.icq.integration.listeners.MetaAckListener;
import ru.caffeineim.protocols.icq.integration.listeners.OurStatusListener;

/**
 * <p>Created by 30.03.2008
 *   @author Samolisov Pavel
 */
public class ChangePasswordTest implements OurStatusListener, MetaAckListener{

	private static Log log = LogFactory.getLog(ChangePasswordTest.class);

	private static final String SERVER = "login.icq.com";
	private static final int PORT = 5190;

	private OscarConnection con;
	private String newPassword;

	public ChangePasswordTest(String login, String password, String newPassword) {
		this.newPassword = newPassword;
		con = new OscarConnection(SERVER, PORT, login, password);

		con.addMetaAckListener(this);
		con.addOurStatusListener(this);

		con.connect();
	}

	public void onMetaAck(MetaAckEvent e) {

	}

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Use : ChangePasswordTest MY_UIN MY_PASSWORD NEW_PASSWORD");
		} else {
			new ChangePasswordTest(args[0], args[1], args[2]);
		}
	}

	public void onAuthorizationFailed(LoginErrorEvent e) {
		con.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onLogin() {
		try {
			OscarInterface.changePassword(con, newPassword);
		}
		catch (ConvertStringException ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	public void onLogout(Exception e) {
		con.close();
		log.error("Logout ", e);
		System.exit(1);
	}

	public void onStatusResponse(StatusEvent e) {
		// XXX
	}
}
