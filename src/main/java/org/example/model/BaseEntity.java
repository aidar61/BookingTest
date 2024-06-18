package org.example.model;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.utils.JsonUtils;

@Slf4j
public abstract class BaseEntity {

    public String toJson() {
        return JsonUtils.convertToJson(this);
    }

    public boolean isEquals(Object obj) {
        try {
            Assertions.assertThat(this)
                    .usingRecursiveComparison()
                    .ignoringActualNullFields()
                    .ignoringExpectedNullFields()
                    .isEqualTo(obj);
            log.info("Entities is correct \n {},\n {}", obj.toString(), this.toString());
            return true;
        } catch (AssertionError e) {
            log.error("Objects are not equals", e);
            return false;
        }
    }
}
