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
import ru.caffeineim.protocols.icq.integration.OscarInterface;
import ru.caffeineim.protocols.icq.integration.events.LoginErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaAffilationsUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaBasicUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaEmailUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaInterestsUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaMoreUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaNoteUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaShortUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.MetaWorkUserInfoEvent;
import ru.caffeineim.protocols.icq.integration.events.StatusEvent;
import ru.caffeineim.protocols.icq.integration.events.UINRegistrationFailedEvent;
import ru.caffeineim.protocols.icq.integration.events.UINRegistrationSuccessEvent;
import ru.caffeineim.protocols.icq.integration.listeners.MetaInfoListener;
import ru.caffeineim.protocols.icq.integration.listeners.OurStatusListener;

/**
 * <p>Created by 30.03.2008
 *   @author Samolisov Pavel
 */
public class FindUsersEventsTest implements MetaInfoListener, OurStatusListener {

	private static Log log = LogFactory.getLog(FindUsersEventsTest.class);

	private static final String SERVER = "login.icq.com";
    private static final int PORT = 5190;
    private static final String UIN = "426981083";

    private OscarConnection connection;

    public FindUsersEventsTest(String uin, String password) {
    	connection = new OscarConnection(SERVER, PORT, uin, password);

    	connection.addMetaInfoListener(this);
        connection.addOurStatusListener(this);

        connection.connect();
    }

	public void onAffilationsUserInfo(MetaAffilationsUserInfoEvent e) {
	}

	public void onBasicUserInfo(MetaBasicUserInfoEvent e) {
	}

	public void onEmailUserInfo(MetaEmailUserInfoEvent e) {
	}

	public void onInterestsUserInfo(MetaInterestsUserInfoEvent e) {
	}

	public void onMoreUserInfo(MetaMoreUserInfoEvent e) {
	}

	public void onNotesUserInfo(MetaNoteUserInfoEvent e) {
	}

	public void onShortUserInfo(MetaShortUserInfoEvent e) {
	}

	public void onWorkUserInfo(MetaWorkUserInfoEvent e) {
	}

	public void onRegisterNewUINFailed(UINRegistrationFailedEvent e) {
	}

	public void onRegisterNewUINSuccess(UINRegistrationSuccessEvent e) {
	}

	public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use : FindUsersEventsTest MY_UIN MY_PASSWORD");
        } else {
            new FindUsersEventsTest(args[0], args[1]);
        }
    }

	public void onAuthorizationFailed(LoginErrorEvent e) {
		connection.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onLogin() {
		OscarInterface.findUsersByUIN(connection, UIN);
	}

	public void onLogout(Exception e) {
		connection.close();
		log.error("Logout ", e);
		System.exit(1);
	}

	public void onStatusResponse(StatusEvent e) {
		// XXX
	}
}
