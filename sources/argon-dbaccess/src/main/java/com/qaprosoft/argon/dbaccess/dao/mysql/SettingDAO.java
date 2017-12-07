package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Setting;

public interface SettingDAO
{

	void createSetting(Setting setting);

	void updateSetting(Setting setting);

	Setting getSettingById(Long id);

	void deleteSettingById(Long id);

}