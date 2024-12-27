package java15.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java15.dto.request.MenuItemRequest;
import java15.dto.response.MenuItemResponse;
import java15.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java15.models.MenuItem;
import java15.repository.MenuItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItemResponse> getAllMenuItems() {
        return menuItemRepository.findAll().stream()
                .map(menuItem -> new MenuItemResponse(menuItem.getId(), menuItem.getName(), menuItem.getImageUrl(),
                        menuItem.getPrice(), menuItem.getDescription(), menuItem.isVegetarian()))
                .collect(Collectors.toList());
    }

    @Override
    public MenuItemResponse getMenuItemById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: " + id);
        }

        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MenuItem with ID " + id + " not found"));

        return new MenuItemResponse(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getImageUrl(),
                menuItem.getPrice(),
                menuItem.getDescription(),
                menuItem.isVegetarian()
        );
    }

    @Override
    public MenuItemResponse createMenuItem(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemRequest.getName());
        menuItem.setImageUrl(menuItemRequest.getImageUrl());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setVegetarian(menuItemRequest.isVegetarian());
        menuItem = menuItemRepository.save(menuItem);
        return new MenuItemResponse(menuItem.getId(), menuItem.getName(), menuItem.getImageUrl(),
                menuItem.getPrice(), menuItem.getDescription(), menuItem.isVegetarian());
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new EntityNotFoundException("MenuItem с ID " + id + " не существует");
        }
        menuItemRepository.deleteById(id);
    }
}