package java15.service;

import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;

import java.util.List;

public interface MenuItemService {

    List<MenuItemResponse> getAllMenuItems();

    MenuItemResponse getMenuItemById(Long id);

    MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest);

    void deleteMenuItem(Long id);
}
