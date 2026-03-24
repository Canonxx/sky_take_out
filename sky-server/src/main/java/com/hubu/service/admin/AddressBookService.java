package com.hubu.service.admin;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.AddressBookDTO;
import com.hubu.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    void save(AddressBookDTO addressBookDTO);

    List<AddressBook> list();

    void setDefault(Long id);

    AddressBook getById(Long id);

    void update(AddressBookDTO addressBookDTO);

    void deleteById(Long id);

    AddressBook getDefault();
}
