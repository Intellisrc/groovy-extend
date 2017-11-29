package jp.sharelock.groovy

import spock.lang.Specification


/**
 * @since 17/11/29.
 */
class MapExtTest extends Specification {
    def "Map to Query"() {
        setup:
            def params = [
                    one : 1,
                    two : 2,
                    three : "third element"
            ]
        when:
            def query = MapExt.toQueryString(params)
            println query
        then:
            assert query == "one=1&two=2&three=third+element"
    }
}