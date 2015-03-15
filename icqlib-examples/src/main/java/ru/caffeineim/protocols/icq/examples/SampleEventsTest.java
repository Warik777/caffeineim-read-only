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
import ru.caffeineim.protocols.icq.integration.events.IncomingMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingUrlEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingUserEvent;
import ru.caffeineim.protocols.icq.integration.events.LoginErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageAckEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageMissedEvent;
import ru.caffeineim.protocols.icq.integration.events.OffgoingUserEvent;
import ru.caffeineim.protocols.icq.integration.events.OfflineMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.StatusEvent;
import ru.caffeineim.protocols.icq.integration.listeners.MessagingListener;
import ru.caffeineim.protocols.icq.integration.listeners.OurStatusListener;
import ru.caffeineim.protocols.icq.integration.listeners.UserStatusListener;
import ru.caffeineim.protocols.icq.setting.enumerations.StatusModeEnum;

/**
 * <p>Created by 22.03.2008
 *   @author Samolisov Pavel
 */
public class SampleEventsTest implements MessagingListener, UserStatusListener, OurStatusListener {

	private static Log log = LogFactory.getLog(SampleEventsTest.class);

	private static final String SERVER = "login.icq.com";
    private static final int PORT = 5190;

    private OscarConnection connection;

    public SampleEventsTest(String uin, String password) {
    	connection = new OscarConnection(SERVER, PORT, uin, password);

        connection.addMessagingListener(this);
        connection.addUserStatusListener(this);
        connection.addOurStatusListener(this);

        connection.connect();
    }

    public void onIncomingMessage(IncomingMessageEvent e) {
        log.info("Incoming message from " + e.getSenderID() + ": " + e.getMessage());
    }

    public void onIncomingUrl(IncomingUrlEvent e) {
    	log.info("Incoming url from " + e.getSenderID() + ": " + e.getUrl());
    }

    public void onOfflineMessage(OfflineMessageEvent e) {
        log.info(e.getSenderUin() + " sent new offline message");
        log.info(" text: " + e.getMessage());
        log.info(" date: " + e.getSendDate());
        log.info(" type: " + e.getMessageType());
        log.info(" flag: " + e.getMessageFlag());
    }

    public void onOffgoingUser(OffgoingUserEvent e) {
    	log.info(e.getOffgoingUserId() + " went offline.");
    }

    public void onIncomingUser(IncomingUserEvent e) {
    	log.info(e.getIncomingUserId() + " has signed on.");
    }

    public void onMessageMissed(MessageMissedEvent e) {
    	log.warn("Message from " + e.getUin() + " can't be recieved because " + e.getReason());
	}

    public void onMessageError(MessageErrorEvent e) {
        log.warn("Message error code " + e.getError().getCode() + " occurred");
    }

    public void onMessageAck(MessageAckEvent e) {
    	log.info("MessageAck " + e.getRcptUin());
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use : SampleEventsTest MY_UIN MY_PASSWORD");
        } else {
            new SampleEventsTest(args[0], args[1]);
        }
    }

	public void onLogin() {
		OscarInterface.changeStatus(connection, new StatusModeEnum(StatusModeEnum.ONLINE));
        OscarInterface.requestOfflineMessages(connection);
	}

    public void onLogout(Exception e) {
        connection.close();
        log.error("Logout ", e);
        System.exit(1);
    }

	public void onAuthorizationFailed(LoginErrorEvent e) {
		connection.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onStatusResponse(StatusEvent e) {
		// XXX
	}
}
