package com.intellisrc.groovy

import groovy.transform.CompileStatic

/**
 * @since 2023/10/11.
 */
@CompileStatic
class ObjectExt {
    @Deprecated // Use `implements ToMap() instead`
    static Map<String,Object> toMap(Object self) {
        return self.class.declaredFields.findAll {
            ! it.synthetic
        }.collectEntries {
            [(it.name) : self[it.name]]
        }
    }
    @Deprecated // Use `implements ToMap() instead`
    static Map<String,Object> toSnakeMap(Object self) {
        return toMap(self).collectEntries {
            [(StringExt.toSnakeCase(it.key)) : it.value]
        }
    }
}
