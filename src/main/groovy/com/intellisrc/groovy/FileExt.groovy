package com.intellisrc.groovy

import groovy.transform.CompileStatic
import groovy.transform.stc.ClosureParams
import groovy.transform.stc.SimpleType

import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission
import java.nio.file.DirectoryStream
import java.nio.file.Path

/**
 * @since 2/17/18.
 */
@CompileStatic
class FileExt {
    static int getLines(final File self) {
        LineNumberReader lr = new LineNumberReader(new FileReader(self))
        while (lr.skip(Long.MAX_VALUE) > 0) { }
        return lr.lineNumber + 1
    }

    static Set<PosixFilePermission> getPermissions(final File self) {
        return Files.getPosixFilePermissions(self.toPath())
    }

    static File ownerPermissions(final File self, boolean read, boolean write, boolean execute) {
        setOwnerReadable(self, read)
        setOwnerWritable(self, write)
        setOwnerExecutable(self, execute)
        return self
    }

    static File groupPermissions(final File self, boolean read, boolean write, boolean execute) {
        setGroupReadable(self, read)
        setGroupWritable(self, write)
        setGroupExecutable(self, execute)
        return self
    }

    static File othersPermissions(final File self, boolean read, boolean write, boolean execute) {
        setOthersReadable(self, read)
        setOthersWritable(self, write)
        setOthersExecutable(self, execute)
        return self
    }

    static File clearPermissions(final File self) {
        ownerPermissions(self, false, false, false)
        groupPermissions(self, false, false, false)
        return othersPermissions(self, false, false, false)
    }

    static File clearOwnerPermissions(final File self) {
        return ownerPermissions(self, false, false, false)
    }

    static File clearGroupPermissions(final File self) {
        return groupPermissions(self, false, false, false)
    }

    static File clearOthersPermissions(final File self) {
        return othersPermissions(self, false, false, false)
    }

    static File setReadable(final File self, boolean owner, boolean group, boolean others) {
        setOwnerReadable(self, owner)
        setGroupReadable(self, group)
        setOthersReadable(self, others)
        return self
    }

    static File setWritable(final File self, boolean owner, boolean group, boolean others) {
        setOwnerWritable(self, owner)
        setGroupWritable(self, group)
        setOthersWritable(self, others)
        return self
    }

    static File setExecutable(final File self, boolean owner, boolean group, boolean others) {
        setOwnerExecutable(self, owner)
        setGroupExecutable(self, group)
        setOthersExecutable(self, others)
        return self
    }

    static File setOwnerReadable(final File self, boolean read) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (read) {
            perms << PosixFilePermission.OWNER_READ
        } else {
            perms.remove(PosixFilePermission.OWNER_READ)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setOwnerWritable(final File self, boolean write) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (write) {
            perms << PosixFilePermission.OWNER_WRITE
        } else {
            perms.remove(PosixFilePermission.OWNER_WRITE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setOwnerExecutable(final File self, boolean execute) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (execute) {
            perms << PosixFilePermission.OWNER_EXECUTE
        } else {
            perms.remove(PosixFilePermission.OWNER_EXECUTE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setGroupReadable(final File self, boolean read) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (read) {
            perms << PosixFilePermission.GROUP_READ
        } else {
            perms.remove(PosixFilePermission.GROUP_READ)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setGroupWritable(final File self, boolean write) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (write) {
            perms << PosixFilePermission.GROUP_WRITE
        } else {
            perms.remove(PosixFilePermission.GROUP_WRITE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setGroupExecutable(final File self, boolean execute) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (execute) {
            perms << PosixFilePermission.GROUP_EXECUTE
        } else {
            perms.remove(PosixFilePermission.GROUP_EXECUTE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setOthersReadable(final File self, boolean read) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (read) {
            perms << PosixFilePermission.OTHERS_READ
        } else {
            perms.remove(PosixFilePermission.OTHERS_READ)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setOthersWritable(final File self, boolean write) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (write) {
            perms << PosixFilePermission.OTHERS_WRITE
        } else {
            perms.remove(PosixFilePermission.OTHERS_WRITE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    static File setOthersExecutable(final File self, boolean execute) {
        Set<PosixFilePermission> perms = Files.getPosixFilePermissions(self.toPath())
        if (execute) {
            perms << PosixFilePermission.OTHERS_EXECUTE
        } else {
            perms.remove(PosixFilePermission.OTHERS_EXECUTE)
        }
        Files.setPosixFilePermissions(self.toPath(), perms)
        return self
    }

    /**
     * Copy a file
     * @param self
     * @param target
     * @return
     */
    static boolean copyTo(final File self, final File target) {
        return Files.copy(self.toPath(), target.toPath())
    }

    /**
     * Move a file
     * @param self
     * @param target
     * @return
     */
    static boolean moveTo(final File self, final File target) {
        return Files.move(self.toPath(), target.toPath())
    }
    /**
     * Create link or symlink
     * @param self
     * @param target
     * @param hard : create hard link instead
     * @return
     */
    static boolean linkTo(final File self, final File target, boolean hard = false) {
        return hard ? Files.createLink(target.toPath(), self.toPath()) : Files.createSymbolicLink(target.toPath(), self.toPath())
    }

    /**
     * Link or copy a file
     * This is useful to prevent duplicated files if possible (free space management)
     * @param self
     * @param target
     * @param force : remove target file if exists
     * @return
     */
    static boolean hardLinkOrCopyTo(final File self, final File target, boolean force = false) {
        if (force && target.exists()) {
            target.delete()
        }
        try {
            linkTo(self, target, true)
        } catch (Exception e) {
            copyTo(self, target)
        }
    }

    /**
     * Return all files asynchronously (e.g. directories with many files)
     *    file.eachFileAsync {
     *        File f ->
     *    }
     * @param self
     * @param closure
     * @param filter
     */
    static void eachFileAsync(final File self, @ClosureParams(value=SimpleType.class,options="java.io.File") final Closure closure) {
        eachFileMatchAsync(self, "*.*", closure)
    }

    /**
     * Return files asynchronously which match a filter (e.g. directories with many files)
     *    file.eachFileMatchAsync("*.{jpg,png,gif}) {
     *        File f ->
     *    }
     * @param self
     * @param closure
     * @param filter
     */
    static void eachFileMatchAsync(final File self, final String filter, @ClosureParams(value=SimpleType.class,options="java.io.File") final Closure closure) {
        if(self.exists() && self.isDirectory()) {
            DirectoryStream<Path> stream = null
            try {
                stream = Files.newDirectoryStream(self.toPath(), filter)
                stream.each {
                    Path p ->
                        closure(p.toFile())
                }
            } catch (IOException ex) {
                ex.printStackTrace()
            } finally {
                stream.close()
            }
        }
    }
}