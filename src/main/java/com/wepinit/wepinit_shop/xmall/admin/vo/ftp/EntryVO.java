/*******************************************************************
                     PMGroupKorea Co., Ltd.
Copyright PMGroupKorea Co., Ltd. 2005. All rights reserved.
No part of this work covered by the copyright hereon may be reproduced,
stored in a retrieval system, in any form or by any means, electronic,
mechanical, photocopying, recording or otherwise, without the prior
written permission of PMGroupKorea Co., Ltd.
SOLUTION    :   XMaLL4 for Spring
FILE_NAME   :
DATE        :   2018.1.29
AUTHOR      :   PMGK S/W Engineer   <contact@gmail.com>
DESCRIPTION :
FUNCTIONS   :
HISTORY     :
REMARKS     :
******************************************************************/
package com.wepinit.wepinit_shop.xmall.admin.vo.ftp;


import com.wepinit.wepinit_shop.xmall.admin.service.ftp.FTPDefault;

import java.io.File;
import java.util.Date;

public class EntryVO {
	private String name;
	private String buf;
	private boolean canRead;
	private long length;
	private String convertFileSize;
	private String date;
	private String link;
	private boolean directory;
	private String type;
	
	private File entry;
	
	public EntryVO() {}	
	public EntryVO(File entry) {
		this.name = FTPDefault.toHangul(entry.getAbsolutePath());
		this.buf = entry.getAbsolutePath();
		this.canRead = entry.canRead();
		this.length = entry.length();
		this.convertFileSize = FTPDefault.convertFileSize(this.length);
		this.date = FTPDefault.dateFormat.format(new Date(entry.lastModified()));
		this.directory = entry.isDirectory();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBuf(String buf) {
		this.buf = buf;
	}
	public String getBuf() {
		return buf;
	}
	public void setEntry(File entry) {
		this.entry = entry;
	}
	public File getEntry() {
		return entry;
	}
	public boolean isCanRead() {
		return canRead;
	}
	public String getConvertFileSize() {
		return convertFileSize;
	}
	public String getDate() {
		return date;
	}
	public long getLength() {
		return length;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLink() {
		return link;
	}
	public boolean isDirectory() {
		return directory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
