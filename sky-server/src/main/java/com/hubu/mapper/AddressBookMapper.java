package com.hubu.mapper;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.entity.AddressBook;

import java.util.List;

public interface AddressBookMapper {
    void insert(AddressBook addressBook);

    List<AddressBook> listByUserId(Long userId);

    // 将当前用户所有地址设为非默认
    void clearDefaultByUserId(Long userId);

    // 根据地址id设为默认
    void setDefaultById(Long id);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void deleteById(Long id);
}
