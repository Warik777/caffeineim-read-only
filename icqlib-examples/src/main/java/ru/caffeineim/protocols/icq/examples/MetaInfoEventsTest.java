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

import java.util.Iterator;
import java.util.Map.Entry;

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
 * <p>Created by 22.03.2008
 *   @author Samolisov Pavel
 */
public class MetaInfoEventsTest implements MetaInfoListener, OurStatusListener {

	private static Log log = LogFactory.getLog(MetaInfoEventsTest.class);

	private static final String SERVER = "login.icq.com";
    private static final int PORT = 5190;

    private static final String UIN1 = "217709";

    private OscarConnection connection;

    public MetaInfoEventsTest(String uin, String password) {
        connection = new OscarConnection(SERVER, PORT, uin, password);

        connection.addMetaInfoListener(this);
        connection.addOurStatusListener(this);

        connection.connect();
    }

    public void onShortUserInfo(MetaShortUserInfoEvent e) {
        log.info("Short User Info: ");
        log.info("  Nick Name = "  + e.getNickName());
        log.info("  First Name = " + e.getFirstName());
        log.info("  Last Name = "  + e.getLastName());
        log.info("  Email = "      + e.getEmail());
        log.info("  Auth = "       + e.isAuth());
    }

    public void onBasicUserInfo(MetaBasicUserInfoEvent e) {
        log.info("Basic User Info: ");
        log.info("  Nick Name = "  + e.getNickName());
        log.info("  First Name = " + e.getFirstName());
        log.info("  Last Name = "  + e.getLastName());
        log.info("  Email = "      + e.getEmail());
        log.info("  Home City = "  + e.getHomeCity());
        log.info("  Home State = " + e.getHomeState());
        log.info("  Home Phone = " + e.getHomePhone());
        log.info("  Home Fax = "   + e.getHomeFax());
        log.info("  Home Address = "  + e.getHomeAddress());
        log.info("  Cell Phone = "  + e.getCellPhone());
        log.info("  Zip = "         + e.getZipCode());
        log.info("  Home Country = "  + e.getHomeCountry());
        log.info("  GMT offset = "  + e.getTimeZone());
        log.info("  Auth = "       + e.isAuth());
        log.info("  WebAware = "       + e.isWebaware());
        log.info("  DirectConnection = " + e.isDirectConnection());
        log.info("  PublishPrimaryEmail = " + e.isPublishPrimaryEmail());
    }

    public void onEmailUserInfo(MetaEmailUserInfoEvent e) {
        log.info("Email User Info: ");
        for (int i = 0; i < e.getEmails().size(); i++)
            log.info("Email: " + e.getEmails().get(i));
    }

    public void onWorkUserInfo(MetaWorkUserInfoEvent e) {
        log.info("Work User Info: ");
        log.info("  Work City = "  + e.getWorkCity());
        log.info("  Work State = "  + e.getWorkState());
        log.info("  Work Phone = "  + e.getWorkPhone());
        log.info("  Work Fax = "  + e.getWorkFax());
        log.info("  Work Address = "  + e.getWorkAddress());
        log.info("  Work Zip = "  + e.getWorkZip());
        log.info("  Work Country = "  + e.getWorkCountry());
        log.info("  Work Company = "  + e.getWorkCompany());
        log.info("  Work Department = "  + e.getWorkDepartment());
        log.info("  Work Position = "  + e.getWorkPosition());
        log.info("  Work WebPage = "  + e.getWorkWebPage());
        log.info("  Work Occupation = "  + e.getWorkOccupation());
    }

    public void onMoreUserInfo(MetaMoreUserInfoEvent e) {
        log.info("More User Info");
        log.info(" age = " + e.getAge());
        log.info(" gender = " + e.getGender());
        log.info(" homePage = " + e.getHomePage());
        log.info(" birth = " + e.getBirth());
        log.info(" languages:");
        for (int i = 0; i < e.getLanguages().size(); i++) {
            log.info(e.getLanguages().get(i));
        }
        log.info(" original City = " + e.getOriginalCity());
        log.info(" original State = " + e.getOriginalState());
        log.info(" original Country = " + e.getOriginalCountry());
        log.info(" marital Status = " + e.getMaritalStatus());
    }

    public void onNotesUserInfo(MetaNoteUserInfoEvent e) {
        log.info(" About info = " + e.getNote());
    }

    public void onInterestsUserInfo(MetaInterestsUserInfoEvent e) {
        log.info("Interests User Info: ");
        for (Iterator iter = e.getInterests().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            log.info("Category: " + entry.getKey() + " interest: " + entry.getValue());
        }
    }

    public void onAffilationsUserInfo(MetaAffilationsUserInfoEvent e) {
        log.info("PostBackgrounds User Info: ");
        for (Iterator iter = e.getPostBackgrounds().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            log.info("Category: " + entry.getKey() + " postbackground: " + entry.getValue());
        }

        log.info("Affilations User Info: ");
        for (Iterator iter = e.getAffilations().entrySet().iterator(); iter.hasNext();) {
            Entry entry = (Entry) iter.next();
            log.info("Category: " + entry.getKey() + " affilations: " + entry.getValue());
        }
    }

    public void onRegisterNewUINSuccess(UINRegistrationSuccessEvent e) {
        log.info("Registration of new number complete");
        log.info("New UIN: " + e.getNewUIN());
    }

    public void onRegisterNewUINFailed(UINRegistrationFailedEvent e) {
        log.warn("UIN Registration filed!");
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Use : MetaInfoEventsTest MY_UIN MY_PASSWORD");
        } else {
            new MetaInfoEventsTest(args[0], args[1]);
        }
    }

	public void onAuthorizationFailed(LoginErrorEvent e) {
		connection.close();
		log.error("Authorization failed: " + e.getErrorMessage());
		System.exit(1);
	}

	public void onLogin() {
        // Запрашиваем мета-информацию
        OscarInterface.requestFullUserInfo(connection, UIN1);
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
