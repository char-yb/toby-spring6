package tobyspring.hellospring;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {

    Sort sort;

    @BeforeEach
    void init() {
        // given
        sort = new Sort();
    }

    @Test
    void sort() {
        // when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // then
        assertThat(list).isEqualTo(Arrays.asList("b", "aa"));
    }

    @Test
    void sort3Items() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        assertThat(list).isEqualTo(Arrays.asList("b", "aa", "ccc"));
    }
}
