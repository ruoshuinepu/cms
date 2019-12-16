package com.smarthaier.service;

import java.util.Set;

public interface ISysMenuService {
    Set<String> selectPermsByUserId(long userId);
}
