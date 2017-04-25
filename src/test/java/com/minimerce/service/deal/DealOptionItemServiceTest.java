package com.minimerce.service.deal;

import com.minimerce.builder.DealOptionItemBuilder;
import com.minimerce.domain.deal.option.item.DealOptionItem;
import com.minimerce.domain.deal.option.item.DealOptionItemRepository;
import com.minimerce.support.util.Yn;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by gemini on 24/04/2017.
 */
public class DealOptionItemServiceTest {
    private DealOptionItemService optionItemService;
    private DealOptionItemRepository mockOptionItemRepository;

    @Before
    public void setUp() {
        mockOptionItemRepository = mock(DealOptionItemRepository.class);
        optionItemService = new DealOptionItemService(mockOptionItemRepository);
    }

    @Test
    public void testShouldBeSaveNewOptionItem() {
        DealOptionItem optionItem = DealOptionItemBuilder.aDealOptionItem().withId(null).build();
        optionItemService.save(optionItem);
        verify(mockOptionItemRepository, times(1)).save(optionItem);
    }

    @Test
    public void testShouldBeUpdateOptionItem() {
        DealOptionItem optionItem = DealOptionItemBuilder.aDealOptionItem().withId(1L).build();
        optionItemService.update(optionItem);
        verify(mockOptionItemRepository, times(1)).save(optionItem);
    }

    @Test
    public void testShouldBeDeleteOptionItem() {
        DealOptionItem optionItem = DealOptionItemBuilder.aDealOptionItem().withDeleted(Yn.N).build();
        when(mockOptionItemRepository.findOne(1L)).thenReturn(optionItem);

        optionItemService.delete(1L);
        assertThat(optionItem.getDeleted(), is(Yn.Y));
    }
}