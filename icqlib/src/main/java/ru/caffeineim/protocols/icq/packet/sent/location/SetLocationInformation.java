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
package ru.caffeineim.protocols.icq.packet.sent.location;

import ru.caffeineim.protocols.icq.Flap;
import ru.caffeineim.protocols.icq.RawData;
import ru.caffeineim.protocols.icq.Snac;
import ru.caffeineim.protocols.icq.Tlv;
import ru.caffeineim.protocols.icq.setting.enumerations.XStatusModeEnum;


/**
 * <p>Created by
 *   @author Fabrice Michellonet
 *   @author Samolisov Pavel
 */
public class SetLocationInformation extends Flap {

	private static final byte[] AIM_SERVER_RELAY = { 0x09, 0x46, 0x13, 0x49,
			0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44, 0x45, 0x53,
			0x54, 0x00, 0x00 };

	private static final byte[] AIM_RECIVE_FROM_ICQ = { 0x09, 0x46,
		0x13, 0x4D, 0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44,
		0x45, 0x53, 0x54, 0x00, 0x00 };
	
	private static final byte[] AIM_SUPPORT_FILE_TRANSFERS = { 0x09, 0x46,
			0x13, 0x43, 0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44,
			0x45, 0x53, 0x54, 0x00, 0x00 };

	private static final byte[] AIM_LIST_SHORT_CAPS = { 0x09, 0x46, 0x00, 0x00,
			0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44, 0x45, 0x53,
			0x54, 0x00, 0x00 };
	
	private static final byte[] UTF8_SUPPORT_CAPS = { 0x09, 0x46, 0x13, 0x4E,
		0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44, 0x45, 0x53,
		0x54, 0x00, 0x00 };

	private static final byte[] AIM_ICQ_RTF_CAPS = { (byte) 0x97, (byte) 0xB1,
			0x27, 0x51, 0x24, 0x3C, 0x43, 0x34, (byte) 0xAD, 0x22, (byte) 0xD6,
			(byte) 0xAB, (byte) 0xF7, 0x3f, (byte) 0x14, (byte) 0x92 };

	private static final byte[] ICQ_CAPS = { 0x09, 0x46, 0x13, 0x44, 0x4C,
			0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44, 0x45, 0x53, 0x54,
			0x00, 0x00 };

	private static final byte[] ICQ_DEVILS_CAPS = { 0x09, 0x46, 0x13, 0x4C,
			0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44, 0x45, 0x53,
			0x54, 0x00, 0x00 };
	
	private static final byte[] ROUTE_FINDER_CAPS = { 0x09, 0x46,
		0x13, 0x44, 0x4C, 0x7F, 0x11, (byte) 0xD1, (byte) 0x82, 0x22, 0x44,
		0x45, 0x53, 0x54, 0x00, 0x00 };
	
	private static final byte[] ICQ_XTRAZ_CAPS = { (byte)0x1A, 0x09,
		0x3C, 0x6C, (byte)0xD7, (byte)0xFD, 0x4E, (byte) 0xC5, (byte) 0x9D, 0x51, (byte)0xA6,
		0x47, 0x4E, 0x34, (byte)0xF5, (byte)0xA0 };
	
	// На будущее. Мы все таки будем CAFFEINE MESSANGER'OM
	//private static final byte[] CAFFEINE_MESSANGER_CAPS = { 'C', 'a', 'f',
	//'f', 'e', 'i', 'n', 'e', ' ', 0, 0, 0, 0, 0, 0, 0};
	 

	private static final byte[] CAFFEINE_MESSANGER_CAPS = { 0x56, 0x3F,
			(byte) 0xC8, 0x09, 0x0B, 0x6F, 0x41, 'Q', 'I', 'P', ' ', '2', '0',
			'0', '5', 'a' };

	public SetLocationInformation() {
		super(2);
		SendLocationInformation(new XStatusModeEnum(0));
	}

	/**
	 * Посылаем SNAC (2, 4) вместе с дополнительным статусом
	 * 
	 * @param mode
	 */
	public SetLocationInformation(XStatusModeEnum mode) {
		super(2);
		SendLocationInformation(mode);
	}

	/**
	 * Отправляем серверу данные о своих возможностях а также иконку Custom
	 * Status'а
	 * 
	 * @param mode Custom Status
	 */
	public void SendLocationInformation(XStatusModeEnum mode) {
		Snac snac = new Snac(0x02, 0x04, 0x0, 0x0, 0x00);

		Tlv capaTlv = new Tlv(new RawData(AIM_SERVER_RELAY), 5);
		// TODO RTF don't support now
		//capaTlv.appendRawDataToTlv(new RawData(AIM_ICQ_RTF_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(AIM_SUPPORT_FILE_TRANSFERS));
		capaTlv.appendRawDataToTlv(new RawData(AIM_RECIVE_FROM_ICQ));
		capaTlv.appendRawDataToTlv(new RawData(AIM_LIST_SHORT_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(ICQ_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(ICQ_DEVILS_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(ROUTE_FINDER_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(UTF8_SUPPORT_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(ICQ_XTRAZ_CAPS));
		capaTlv.appendRawDataToTlv(new RawData(CAFFEINE_MESSANGER_CAPS));

		if (mode.getXStatus() > 0) {
			// send XStatus code
			capaTlv.appendRawDataToTlv(new RawData(mode.getXStatusData()));
		}		

		snac.addTlvToSnac(capaTlv);
		this.addSnac(snac);
	}
}