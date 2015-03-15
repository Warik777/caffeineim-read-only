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
import ru.caffeineim.protocols.icq.integration.events.IncomingMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingUrlEvent;
import ru.caffeineim.protocols.icq.integration.events.LoginErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageAckEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageMissedEvent;
import ru.caffeineim.protocols.icq.integration.events.OfflineMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.StatusEvent;
import ru.caffeineim.protocols.icq.integration.events.XStatusRequestEvent;
import ru.caffeineim.protocols.icq.integration.events.XStatusResponseEvent;
import ru.caffeineim.protocols.icq.integration.listeners.MessagingListener;
import ru.caffeineim.protocols.icq.integration.listeners.OurStatusListener;
import ru.caffeineim.protocols.icq.integration.listeners.XStatusListener;
import ru.caffeineim.protocols.icq.setting.enumerations.StatusModeEnum;
import ru.caffeineim.protocols.icq.setting.enumerations.XStatusModeEnum;

/**
 * <p>Created by 22.03.2008
 *   @author Samolisov Pavel
 */
public class XStatusEventsTest implements MessagingListener, XStatusListener, OurStatusListener {

	private static Log log = LogFactory.getLog(XStatusEventsTest.class);

	private static final String SERVER = "login.icq.com";
    private static final int PORT = 5190;

    private static final String XSTATUS_BASE_STRING = "Любовь приходит";
    private static final String XSTATUS_EXT_STRING = "и уходит, а кушать хочется всегда";

    private OscarConnection connection;

    public XStatusEventsTest(String uin, String password) {
    	connection = new OscarConnection(SERVER, PORT, uin, password);

        connection.addMessagingListener(this);
        connection.addXStatusListener(this);
        connection.addOurStatusListener(this);

        connection.connect();
    }

    public void onIncomingMessage(IncomingMessageEvent e) {
    	log.info("Incoming message from " + e.getSenderID() + ": " + e.getMessage());
        OscarInterface.sendXStatusRequest(connection, e.getSenderID());
    }

    public void onIncomingUrl(IncomingUrlEvent e) {
    	log.info("Incoming url from " + e.getSenderID() + ": " + e.getUrl());
    }

    public void onOfflineMessage(OfflineMessageEvent e) {
        log.info(e.getSenderUin() + " sent : " + e.getMessage() + " while i was offline");
    }

    public void onMessageMissed(MessageMissedEvent e) {
		log.warn("Message from " + e.getUin() + " can't be recieved because " + e.getReason());
	}

    public void onMessageError(MessageErrorEvent e) {
        log.warn("Message error code " + e.getError().getCode() + " occurred");
    }

    public void onMessageAck(MessageAckEvent e) {
    	log.info("Message Ack " + e.getMessageType() + " " + e.getRcptUin());
    }

    public void onXStatusRequest(XStatusRequestEvent e) {
    	try {
    		OscarInterface.sendXStatus(connection, new XStatusModeEnum(XStatusModeEnum.LOVE),
    				XSTATUS_BASE_STRING, XSTATUS_EXT_STRING, e.getTime(), e.getMsgID(), e.getSenderID(), e.getSenderTcpVersion());
    	}
    	catch (ConvertStringException ex) {
    		log.error(ex.getMessage(), ex);
    	}
    }

    public void onXStatusResponse(XStatusResponseEvent e) {
    	try {
    		OscarInterface.sendExtendedMessage(connection, e.getSenderID(), "Title = " + e.getTitle() + " Description = " + e.getDescription() + " XStatus = " + e.getXStatus().toString());
    	}
    	catch (ConvertStringException ex) {
    		log.error(ex.getMessage(), ex);
    	}
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use : XStatusEventsTest MY_UIN MY_PASSWORD");
        } else {
            new XStatusEventsTest(args[0], args[1]);
        }
    }

	public void onLogin() {
    	OscarInterface.changeStatus(connection, new StatusModeEnum(StatusModeEnum.ONLINE));
    	OscarInterface.changeXStatus(connection, new XStatusModeEnum(XStatusModeEnum.LOVE));
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
