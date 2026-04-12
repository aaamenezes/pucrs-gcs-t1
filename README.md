# pucrs-gcs-t1

- [pucrs-gcs-t1](#pucrs-gcs-t1)
- [📦 Gerenciamento de Branches e Fluxo de Trabalho](#-gerenciamento-de-branches-e-fluxo-de-trabalho)
  - [🌳 Estrutura de Branches](#-estrutura-de-branches)
  - [🤔 Quando usar feature ou hotfix](#-quando-usar-feature-ou-hotfix)
  - [🚀 Fluxo de Desenvolvimento de Features e Correções de Bugs](#-fluxo-de-desenvolvimento-de-features-e-correções-de-bugs)
    - [1. Criar uma branch de feature](#1-criar-uma-branch-de-feature)
    - [2. Desenvolver a funcionalidade](#2-desenvolver-a-funcionalidade)
    - [3. Enviar a branch para o repositório remoto](#3-enviar-a-branch-para-o-repositório-remoto)
    - [4. Criar Pull Request (PR)](#4-criar-pull-request-pr)
    - [5. Aprovação do Pull Request](#5-aprovação-do-pull-request)
    - [6. Merge do Pull Request](#6-merge-do-pull-request)
    - [7. Remoção da branch local (opcional, mas recomendado)](#7-remoção-da-branch-local-opcional-mas-recomendado)
  - [⚠️ Regras Importantes](#️-regras-importantes)
    - [❌ Proibido commit direto na main](#-proibido-commit-direto-na-main)
    - [✅ Boas Práticas](#-boas-práticas)
    - [📌 Resumo do Fluxo](#-resumo-do-fluxo)


# 📦 Gerenciamento de Branches e Fluxo de Trabalho

Este documento descreve o padrão de organização de branches e o fluxo de trabalho adotado pela equipe para o desenvolvimento do projeto.

O objetivo é garantir colaboração eficiente entre os integrantes, manter a qualidade do código e evitar conflitos durante o desenvolvimento.

## 🌳 Estrutura de Branches

A estrutura de branches do projeto segue o padrão abaixo:

- `main`: branch principal, contém o código estável e pronto para entrega.
- `feature/*`: branches para desenvolvimento de novas funcionalidades.
- `hotfix/*`: branches para correção de bugs críticos encontrados na `main`.

Exemplos:

```bash
feature/login-usuario
feature/cadastro-cliente
feature/listagem-produtos

hotfix/corrige-bug-login
hotfix/erro-calculo-total
hotfix/corrige-validacao-formulario
```

## 🤔 Quando usar feature ou hotfix

Antes de criar uma branch, pense no objetivo da sua alteração.

Se você está **adicionando algo novo ao sistema** (uma funcionalidade, melhoria ou evolução), use uma branch do tipo `feature/*`.

Por outro lado, se você está **corrigindo um erro que já existe no sistema**, especialmente algo que está quebrando ou afetando o funcionamento na `main`, use uma branch do tipo `hotfix/*`.

Em resumo: **criou algo novo, é feature; corrigiu algo que estava errado, é hotfix**.

## 🚀 Fluxo de Desenvolvimento de Features e Correções de Bugs

### 1. Criar uma branch de feature

Cada nova funcionalidade deve ser desenvolvida em uma branch própria, criada a partir da `main` com padrão de nome informado acima.

Exemplo:

```bash
git checkout -b feature/nome-da-feature

# ou

git checkout -b hotfix/nome-da-correcao
```

### 2. Desenvolver a funcionalidade

Após implementar a funcionalidade:

```bash
git add .
git commit -m "feat: descrição da funcionalidade"

# ou

git commit -m "fix: descrição da correção"
```

### 3. Enviar a branch para o repositório remoto

```bash
git push origin feature/nome-da-feature

# ou

git push origin hotfix/nome-da-correcao
```

### 4. Criar Pull Request (PR)

- Criar um PR da branch `feature/*` ou `hotfix/*` para `main`.
- O PR deve descrever claramente a funcionalidade implementada nos campos título e descrição.

### 5. Aprovação do Pull Request

Cada PR deve ter **no mínimo 2 aprovações** de outros integrantes do grupo.

### 6. Merge do Pull Request

Após aprovação:

- O responsável pela branch pode realizar o merge.
- Após o merge, o PR deve ser fechado pelo responsávl por aquela alteração.

### 7. Remoção da branch local (opcional, mas recomendado)

Após o merge, mude para a branch `main`, remova a branch `feature/*` criada, e atualize sua branch `main` local com as novas alterações:

```bash
git checkout main
git branch -D feature/nome-da-feature
git pull origin main
```

## ⚠️ Regras Importantes

### ❌ Proibido commit direto na main

É expressamente proibido realizar commits diretamente na branch main.

Motivos:

- Alterações diretas não passam por revisão de código.
- Aumenta o risco de introdução de bugs.
- Dificulta a identificação e correção de problemas.
- Pode atrasar a entrega do projeto.

### ✅ Boas Práticas

- Sempre manter a branch atualizada com a `main` antes de abrir um PR.
- Escrever mensagens de commit claras e objetivas.
- Revisar o código de outros integrantes com atenção.
- Garantir que o código está funcionando antes de solicitar revisão.

### 📌 Resumo do Fluxo

- Criar branch (`feature/*` ou `hotfix/*`)
- Desenvolver e commitar
- Fazer push
- Criar Pull Request
- Aguardar 2 aprovações
- Realizar merge
- Excluir branch local

Esse fluxo garante organização, qualidade e colaboração eficiente entre todos os integrantes do grupo.