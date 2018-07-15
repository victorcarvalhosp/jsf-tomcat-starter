No caso de funcionalidades grandes, normalmente as marcadas com a label **Feature**, ou bugs que podem ser resolvidos com mais calma:

1.  Clicar em **Create merge request** na própria issue
2.  Baixe a branch recém criada através dos comandos ``git fetch`` seguido por um ``git checkout <nome-da-branch>``
3.  Fazer push da branch com o trabalho realizado.
4.  Clicar em "Resolve WIP Status" no "Merge Requests" do Gitlab
5.  Checar o "Remove source branch" e clicar em "Merge when pipeline succeeds"

Para as modificações menores, no geral correções pequenas e ajustes pontuais que precisam ser feitos rapidamente, normalmente marcadas como **Critical** + **Bug** ou **Chore**:

1.  Trabalhar direto na ``master``
2.  Push usando os hooks do Gitlab (https://docs.gitlab.com/ee/user/project/issues/automatic_issue_closing.html)