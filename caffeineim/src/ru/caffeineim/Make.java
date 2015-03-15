/** * Copyright 2008 Caffeine-Soft Group * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at *  * http://www.apache.org/licenses/LICENSE-2.0
 *  * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ru.caffeineim;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import ru.caffeineim.protocols.icq.contacts.ContactList;
import ru.caffeineim.protocols.icq.contacts.Group;
import ru.caffeineim.protocols.icq.core.OscarConnection;
import ru.caffeineim.protocols.icq.exceptions.ConvertStringException;
import ru.caffeineim.protocols.icq.integration.events.ContactListEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingUrlEvent;
import ru.caffeineim.protocols.icq.integration.events.IncomingUserEvent;
import ru.caffeineim.protocols.icq.integration.events.LoginErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageAckEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageErrorEvent;
import ru.caffeineim.protocols.icq.integration.events.MessageMissedEvent;
import ru.caffeineim.protocols.icq.integration.events.OffgoingUserEvent;
import ru.caffeineim.protocols.icq.integration.events.OfflineMessageEvent;
import ru.caffeineim.protocols.icq.integration.events.SsiAuthReplyEvent;
import ru.caffeineim.protocols.icq.integration.events.SsiAuthRequestEvent;
import ru.caffeineim.protocols.icq.integration.events.SsiFutureAuthGrantEvent;
import ru.caffeineim.protocols.icq.integration.events.SsiModifyingAckEvent;
import ru.caffeineim.protocols.icq.integration.events.StatusEvent;
import ru.caffeineim.protocols.icq.integration.listeners.ContactListListener;
import ru.caffeineim.protocols.icq.integration.listeners.MessagingListener;
import ru.caffeineim.protocols.icq.integration.listeners.StatusListener;
import ru.caffeineim.protocols.icq.setting.enumerations.SsiResultModeEnum;
import ru.caffeineim.protocols.icq.tool.OscarInterface;
import ru.caffeineim.gui.ContactsFrame;

/**
 * Описание: класс занимается управлением средой: вяжет гуи с протоколом
 * @version 0.0.1  10.12.2008
 * @author Vladimir Dvinianinov
 * @authot 
 */
public class Make implements MessagingListener, Observer {
    //private OscarConnection connection;
    
   public Make (OscarConnection connection) {
             
       connection.getPacketAnalyser().setDebug(true);
       connection.getPacketAnalyser().setDump(true);
       connection.addMessagingListener(this);
       connection.addObserver(this);
       
       ContactsFrame contactFrame = new ContactsFrame(connection);
       contactFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       contactFrame.setLocation(800, 300);
       contactFrame.setVisible(true);
       //contactFrame.updateContactList(ContactListEvent e);
          
       
   }

    public void onIncomingMessage(IncomingMessageEvent e) {
//        if(historyOn){
//            History.saveMessage(e.getMessage());
//        }
        System.out.println(e.getSenderID()+": "+e.getMessage());
    }

    public void onIncomingUrl(IncomingUrlEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onOfflineMessage(OfflineMessageEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMessageAck(MessageAckEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMessageError(MessageErrorEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onMessageMissed(MessageMissedEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onIncomingUser(IncomingUserEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onOffgoingUser(OffgoingUserEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onStatusChange(StatusEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onLogout() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onAuthorizationFailed(LoginErrorEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateContactList(ContactListEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onSsiModifyingAck(SsiModifyingAckEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onSsiFutureAuthGrant(SsiFutureAuthGrantEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onSsiAuthRequest(SsiAuthRequestEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onSsiAuthReply(SsiAuthReplyEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
