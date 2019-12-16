package com.smarthaier.service;

import java.util.Set;

public interface ISysRoleService {
    Set<String> selectRoleKeys(long userId);
}
