# YPermission
动态权限
Step 1. Add the JitPack repository to your build file
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  Step 2. Add the dependency
  dependencies {
	        implementation 'com.github.yuye1124956:YPermission:1.2'
	}