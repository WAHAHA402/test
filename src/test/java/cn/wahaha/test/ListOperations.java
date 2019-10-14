package cn.wahaha.test;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.PredicateUtils;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * @Description: ListOperations
 * @Author: zhangrenwei
 * @Date: 2019-07-24 17:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ListOperations {
    @Test
    public void removeNull() {
        //Remove nulls from a List Using Plain Java
        List<Integer> list = Lists.newArrayList(1, null, 2, null, 3);
        while(list.remove(null));
        Assert.assertThat(list, hasSize(3));

        //we can also use the following simple approach
        List<Integer> list3 = Lists.newArrayList(1, null, 2, null, 3);
        list3.removeAll(Collections.singleton(null));
        Assert.assertThat("size equals 3", list3, hasSize(3));

        //Remove nulls from a List Using Google Guava
        List<Integer> list2 = Lists.newArrayList(1, null, 2, null, 3);
        Iterables.removeIf(list2, Predicates.isNull());
        Assert.assertThat(list2, hasSize(3));

        //if we don’t want to modify the source list, Guava will allow us to create a new, filter list
        List<Integer> listWithoutNulls = Lists.newArrayList(
                Iterables.filter(list, Predicates.notNull())
        );
        Assert.assertThat(listWithoutNulls, hasSize(3));
    }

    @Test
    public void removeNull2() {
        //Remove nulls from a List Using Apache Commons Collections
        List<Integer> list = Lists.newArrayList(1, null, 2, null, 3);
        CollectionUtils.filter(list, PredicateUtils.notNullPredicate());
        Assert.assertThat(list, hasItem(3));

        //Remove nulls from a List Using Lambdas (Java 8)
        List<Integer> list2 = Lists.newArrayList(1, null, 2, null, 3);
        List<Integer> listWithoutNull = list2.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Assert.assertThat(listWithoutNull, hasSize(3));

        list2.removeIf(Objects::isNull);
        Assert.assertThat(list2, hasSize(3));
    }

}
