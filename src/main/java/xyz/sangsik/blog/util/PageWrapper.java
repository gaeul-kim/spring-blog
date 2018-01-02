package xyz.sangsik.blog.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangsik on 2017-12-27.
 */
public class PageWrapper<T> {

    @Getter
    @Setter
    private Page<T> page;

    @Getter
    @Setter
    private String url;

    private List<PageItem> items;
    private int currentNumber;
    private int maxSize;

    public PageWrapper(Page<T> page, String baseUrl) {
        this.page = page;
        this.url = baseUrl;
        this.items = new ArrayList<PageItem>();
        this.currentNumber = page.getNumber() + 1; //start from 1 to match page.page
        this.maxSize = page.getSize();

        int start, size;
        if (page.getTotalPages() <= maxSize) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if (currentNumber <= maxSize - maxSize / 2) {
                start = 1;
                size = maxSize;
            } else if (currentNumber >= page.getTotalPages() - maxSize / 2) {
                start = page.getTotalPages() - maxSize + 1;
                size = maxSize;
            } else {
                start = currentNumber - maxSize / 2;
                size = maxSize;
            }
        }

        for (int i = 0; i < size; i++) {
            items.add(new PageItem(start + i, (start + i) == currentNumber));
        }
    }

    public PageWrapper(Page<T> page) {
        this(page, "");
    }


    public List<PageItem> getItems() {
        return items;
    }

    public int getNumber() {
        return currentNumber;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getSize() {
        return page.getSize();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public boolean isFirstPage() {
        return page.isFirst();
    }

    public boolean isLastPage() {
        return page.isLast();
    }

    public boolean isHasPreviousPage() {
        return page.hasPrevious();
    }

    public boolean isHasNextPage() {
        return page.hasNext();
    }

    public class PageItem {
        private int number;
        private boolean current;

        public PageItem(int number, boolean current) {
            this.number = number;
            this.current = current;
        }

        public int getNumber() {
            return this.number;
        }

        public boolean isCurrent() {
            return this.current;
        }
    }
}