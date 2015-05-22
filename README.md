# smart-copier
Java library for copying and syncing folders

## Usage
<code>
```
File copyFrom = new File("path/to/source/folder");
File copyTo = new File("path/to/destination/folder");

// Copy new files, remove deleted files, copy modified filed but do not touch the other files
ISmartVisitor visitor = new OneWaySyncVisitor();

// Compare files with SHA-256
IUpdatePolicy policy = new UpdateIfHashDifferentPolicy();

SmartCopier sc = new SmartCopier(copyFrom, copyTo);
sc.setVisitor(visitor).setUpdatePolicy(policy).start();
```
</code>
