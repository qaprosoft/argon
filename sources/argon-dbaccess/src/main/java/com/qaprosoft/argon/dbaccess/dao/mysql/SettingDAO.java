package com.qaprosoft.argon.dbaccess.dao.mysql;

import com.qaprosoft.argon.models.db.Setting;

/**
 * @author asemenkov
 * @since 07 Dec 2017
 */
public interface SettingDAO
{

	void createSetting(Setting setting);

	void updateSetting(Setting setting);

	Setting getSettingById(Long id);

	void deleteSettingById(Long id);

}