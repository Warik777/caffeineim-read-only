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
import ru.caffeineim.protocols.icq.integration.listeners.MessagingListener;
import ru.caffeineim.protocols.icq.integration.listeners.OurStatusListener;

/**
 * Демонстрируем работу двух экземпляров {@link OscarConnection} в диалоговом
 * режиме: один экземпляр посылает другому сообщение, в ответ на которое тот отвечает.
 *
 * <p>Created by 14.01.2010
 *   @author Samolisov Pavel
 */
public class DialogTest implements MessagingListener, OurStatusListener {

	private static Log log = LogFactory.getLog(DialogTest.class);

	private static final String SERVER = "login.icq.com";
	private static final int PORT = 5190;

	private OscarConnection connection;

	private String recepient;

	private boolean start;

	public DialogTest(String uin, String password, String recepient, boolean start) {
		this.recepient = recepient;
		this.start = start;

		connection = new OscarConnection(SERVER, PORT, uin, password);

        connection.addMessagingListener(this);
        connection.addOurStatusListener(this);

        connection.connect();
	}

	public void onIncomingMessage(IncomingMessageEvent e) {
		log.info("Incoming message from " + e.getSenderID() + ": " + e.getMessage());
		try {
			OscarInterface.sendBasicMessage(connection, e.getSenderID(), "Hi!");
		} catch (ConvertStringException e1) {
			log.error(e1.getMessage(), e1);
		}
	}

	public void onIncomingUrl(IncomingUrlEvent e) {
		// XXX
	}

	public void onMessageAck(MessageAckEvent e) {
		// XXX
	}

	public void onMessageError(MessageErrorEvent e) {
		// XXX
	}

	public void onMessageMissed(MessageMissedEvent e) {
		// XXX
	}

	public void onOfflineMessage(OfflineMessageEvent e) {
		// XXX
	}

	public void onAuthorizationFailed(LoginErrorEvent e) {
		connection.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onLogin() {
		if (start)
			try {
				Thread.sleep(1000); // Ждем пока залогинится второй аккаунт
				OscarInterface.sendBasicMessage(connection, recepient, "Hello");
			} catch (ConvertStringException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	public void onLogout(Exception e) {
		log.error("UIN " + connection.getUserId() + " has logouted", e);
		connection.close();
		System.exit(1);
	}

	public void onStatusResponse(StatusEvent e) {
		// XXX
	}

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Use : DialogTest UIN1 PASSWORD1 UIN2 PASSWORD2");
        } else {
            new DialogTest(args[0], args[1], args[2], true);
            new DialogTest(args[2], args[3], args[0], false);
        }
    }
}
