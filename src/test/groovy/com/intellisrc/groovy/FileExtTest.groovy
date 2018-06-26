package com.intellisrc.groovy

import spock.lang.Specification

import java.nio.file.attribute.PosixFilePermission
/**
 * @since 2/17/18.
 */
class FileExtTest extends Specification {
    File file
    def setup() {
        file = new File(System.getProperty("java.io.tmpdir") + File.separator + "file.ext.test")
        file << "1 Some content\n"
        file << "2 More lines\n"
        file << "3 More lines\n"
        file << "4 More lines\n"
        file << "5 More lines\n"
        file << "6 More lines\n"
        file << "7 More lines\n"
        file << "8 More lines"
    }
    def cleanup() {
        file.delete()
    }
    def "Counting number of lines"() {
        expect:
            assert FileExt.getLines(file) == 8
    }
    def "Testing clear permissions"() {
        setup :
            FileExt.clearPermissions(file)
        expect :
            assert FileExt.getPermissions(file).empty
    }
    def "Only owner 600"() {
        setup :
            FileExt.clearGroupPermissions(file)
            FileExt.clearOthersPermissions(file)
            FileExt.ownerPermissions(file, true, true, false)
        expect :
            assert FileExt.getPermissions(file).containsAll([
                    PosixFilePermission.OWNER_READ,
                    PosixFilePermission.OWNER_WRITE
            ]) &&! FileExt.getPermissions(file).contains(
                    PosixFilePermission.OWNER_EXECUTE
            ) &&! FileExt.getPermissions(file).contains(
                    PosixFilePermission.GROUP_READ
            )
    }
}
