/**
 * 把 package.json 的 version 单向同步到 Gradle 的版本真源，幂等。
 *
 * 家族约定：changeset version 只 bump package.json，平台版本真源由本脚本
 * 跟随同步。android 端的真源是 gradle.properties 的 LHUI_VERSION
 * （各 library 模块的 versionName 与 Maven 坐标版本号读它，
 * 见 docs/发包流程.md §5.3）。
 *
 * 由 `pnpm changeset:version` 自动调用（见 package.json），也可单独执行：
 *   node scripts/sync-version.mjs          同步
 *   node scripts/sync-version.mjs --check  只校验一致性，不同步（CI 门禁用）
 *
 * 注意：令牌目录 core/src/main/kotlin/top/lhgb/ui/tokens/ 是 codegen 生成物，
 * 本脚本不触碰它。
 */
import fs from 'node:fs'
import path from 'node:path'
import { fileURLToPath } from 'node:url'

const __dirname = path.dirname(fileURLToPath(import.meta.url))
const root = path.resolve(__dirname, '..')
const pkgPath = path.resolve(root, 'package.json')
const gradlePropsPath = path.resolve(root, 'gradle.properties')

const checkOnly = process.argv.includes('--check')

const pkg = JSON.parse(fs.readFileSync(pkgPath, 'utf8'))
const { version } = pkg

if (!version) {
  console.error('package.json 缺少 version 字段')
  process.exit(1)
}

if (!fs.existsSync(gradlePropsPath)) {
  console.error(`未找到 ${gradlePropsPath}`)
  process.exit(1)
}

const content = fs.readFileSync(gradlePropsPath, 'utf8')

// 匹配 LHUI_VERSION=x.y.z（properties 文件格式，允许行首空白）
const propRe = /^[ \t]*LHUI_VERSION[ \t]*=[ \t]*(.+?)[ \t]*$/m

const match = content.match(propRe)
if (!match) {
  console.error(`${gradlePropsPath} 中未找到 LHUI_VERSION，请先手工补上初值`)
  process.exit(1)
}

const current = match[1].trim()

if (current === version) {
  console.log(`✅ 版本一致：${version}（gradle.properties LHUI_VERSION）`)
  process.exit(0)
}

if (checkOnly) {
  console.error(
    `❌ 版本不一致：package.json=${version}，gradle.properties LHUI_VERSION=${current}`,
  )
  process.exit(1)
}

const updated = content.replace(propRe, `LHUI_VERSION=${version}`)
fs.writeFileSync(gradlePropsPath, updated, 'utf8')

console.log(`✅ 已同步版本 ${current} → ${version}（gradle.properties LHUI_VERSION）`)
