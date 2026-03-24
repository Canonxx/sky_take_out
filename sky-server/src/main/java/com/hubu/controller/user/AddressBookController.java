package com.hubu.controller.user;/*
 * @Auther:long
 * @Date:2026/3/23
 * @Description:
 * @VERSON:1.8
 */

import com.hubu.dto.AddressBookDTO;
import com.hubu.entity.AddressBook;
import com.hubu.result.Result;
import com.hubu.service.admin.AddressBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/addressBook")
@Slf4j
@Tag(name = "C端-地址簿接口")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增地址簿
     * @return
     */
    @PostMapping()
    @Operation(summary = "新增地址簿")
    public Result save(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("新增地址簿{}",addressBookDTO);
        addressBookService.save(addressBookDTO);
        log.info("新增地址簿成功！");
        return Result.success();
    }

    /**
     * 根据用户id查询所有地址
     * @return
     */
    @GetMapping("/list")
    @Operation(summary = "查询当前用户所有地址")
    public Result<List<AddressBook>> list() {
        log.info("查询当前用户所有地址");
        List<AddressBook> list = addressBookService.list();
        return Result.success(list);
    }

    /**
     * 设置默认地址
     * @return
     */
    @PutMapping("/default")
    @Operation(summary = "设置默认地址")
    public Result setDefault(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("设置默认地址，id:{}", addressBookDTO.getId());
        addressBookService.setDefault(addressBookDTO.getId());
        return Result.success();
    }

    /**
     * 根据id查询地址簿,用于修改地址簿
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询地址簿")
    public Result<AddressBook> getById(@Parameter(description = "地址簿id") @PathVariable Long id) {
        log.info("查询地址簿，id:{}", id);
        AddressBook addressBook = addressBookService.getById(id);
        return Result.success(addressBook);
    }

    /**
     * 修改地址簿
     * @param addressBookDTO
     * @return
     */
    @PutMapping()
    @Operation(summary = "修改地址簿")
    public Result update(@RequestBody AddressBookDTO addressBookDTO) {
        log.info("修改地址簿{}",addressBookDTO);
        addressBookService.update(addressBookDTO);
        log.info("修改地址簿成功！");
        return Result.success();
    }

    /**
     * 删除地址簿
     * @param id
     * @return
     */
    @DeleteMapping()
    @Operation(summary = "删除地址簿")
    public Result delete(@Parameter(description = "地址簿id") @RequestParam Long id) {
        log.info("删除地址簿，id:{}", id);
        addressBookService.deleteById(id);
        return Result.success();
    }
    /**
     * 根据用户id查询默认地址
     *
     */
    @GetMapping("/default")
    @Operation(summary = "根据用户id查询默认地址")
    public Result<AddressBook> getDefault() {
        log.info("根据用户id查询默认地址");
        AddressBook addressBook = addressBookService.getDefault();
        return Result.success(addressBook);
    }
}