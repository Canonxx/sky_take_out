package com.hubu.service.admin.impl;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.context.BaseContext;
import com.hubu.dto.AddressBookDTO;
import com.hubu.entity.AddressBook;
import com.hubu.mapper.AddressBookMapper;
import com.hubu.service.admin.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AddressBookServiceImpl implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;
    @Override
    public void save(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(0);
        addressBookMapper.insert(addressBook);
    }

    @Override
    public List<AddressBook> list() {
        Long userId = BaseContext.getCurrentId();
        return addressBookMapper.listByUserId(userId);
    }
    @Transactional
    @Override
    public void setDefault(Long id) {
        // 先将当前用户所有地址设为非默认
        Long userId = BaseContext.getCurrentId();
        addressBookMapper.clearDefaultByUserId(userId);
        // 再将指定地址设为默认
        addressBookMapper.setDefaultById(id);
    }

    @Override
    public AddressBook getById(Long id) {
        AddressBook addressBook = addressBookMapper.getById(id);
        if (addressBook != null) {
            return addressBook;
        } else {
            return null;
        }
    }

    /**
     * 修改地址簿
     * @param addressBookDTO
     */
    @Override
    public void update(AddressBookDTO addressBookDTO) {
        AddressBook addressBook = new AddressBook();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        addressBookMapper.update(addressBook);

    }
    /**
     * 删除地址簿
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        addressBookMapper.deleteById(id);
    }
}
