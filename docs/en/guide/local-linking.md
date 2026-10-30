# Local Linking

## Family layout convention

```
lh-ui/                        # family parent directory (not a repo)
├── lh-ui-core/               # token source of truth + codegen
├── lh-ui-android/            # this repo
├── lh-ui-ios/                # sibling repos
├── lh-ui-harmony/
└── lh-ui-windows/
```

When every repo shares the same parent directory, tokens are **linked locally**; when a sibling repo is missing (a single-repo CI clone), the toolchain **falls back to the npm package** automatically.
This mirrors the vite alias guard mechanism of the web bloodline (master plan D18).

## Three-level guard

`scripts/link-tokens.mjs` looks up the token artifacts by priority:

| Priority | Source | Description |
| --- | --- | --- |
| 1 | `../lh-ui-core/dist/android` | Local link: token edits take effect immediately |
| 2 | `node_modules/@lh-ui/core/dist/android` | npm fallback: `@lh-ui/core`'s `files:["dist"]` includes all four platform artifacts |
| 3 | — | Error out — without tokens, the build cannot proceed |

## Daily workflow

```bash
# 1. Edit tokens (in the core repo)
cd ../lh-ui-core
vim tokens/color.json           # or spacing / typography / ...

# 2. Regenerate + verify
pnpm codegen                    # generate the dist/ artifacts for all four platforms
pnpm verify                     # schema + references + WCAG contrast + spec consistency

# 3. Sync back to this repo
cd ../lh-ui-android
pnpm link:tokens                # overwrite core/src/main/kotlin/top/lhgb/ui/tokens/

# 4. Regression
./gradlew :components:button:test
```

## CI gate

```bash
pnpm link:tokens:check          # checks whether tokens are in sync (file-count comparison; exit code 1 if not)
```

## What not to do

- ❌ Hand-editing `core/src/main/kotlin/top/lhgb/ui/tokens/` (generated output, gitignored)
- ❌ Committing generated token files to the repo
- ❌ Writing color or spacing literals in components (e.g. `Color(0xFF2B4C7C)`, `16.dp`, `14.sp`)
- ❌ Committing `file:` / `link:` dependency forms into `build.gradle.kts` (local linking is local-only)

## Troubleshooting

| Symptom | Cause | Fix |
| --- | --- | --- |
| Compile error `unresolved reference: LhTokens` | Tokens not synced | Run `pnpm link:tokens` |
| Token values do not match expectations | The core repo was not re-run through codegen | Run `pnpm codegen` in the core repo, then link again |
| `link:tokens` reports "token artifacts not found" | Sibling repo absent and no npm package installed | Check the family layout, or `pnpm i -D @lh-ui/core` |
| Dark colors look wrong | The light primary was changed but the dark precomputation was not updated | Update `color-dark.json` and re-run (R-n6) |
