package com.qingge.springboot.service;

import com.qingge.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  菜单服务
 * </p>
 *
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
