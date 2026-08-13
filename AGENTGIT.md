# Task: GitHub 저장소 생성 + Add,Commit,Push

다음 순서로 git 저장소를 세팅하고 push까지 진행해:
- 로그인 확인 → 원격 저장소(origin) 있는지 확인 → 없으면 생성 → add/commit/push
- 레포이름: [레포이름] (사용자에게 먼저 확인)

```bash
gh auth status
git remote -v
git init
git add .
git commit -m "Initial commit"
git branch -M main
gh repo create spring-class-content --public --source=. --remote=origin --push

```

**검증**: `git remote -v` 실행 시 `origin` 주소 출력 확인
