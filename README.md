# BrScanSDK Captura Android Demo

O objetivo é dar uma visão técnica do processo de captura do documento de identificação do cliente, suas dependências e seus meios de implementação em sistemas de terceiros.

Serão apresentadas as dependências necessárias para o correto funcionamento dos componentes, exemplos e orientações de como instanciar o processo dentro de uma aplicação e munir o consumidor com um fluxo que detalha todas as integrações que são realizadas durante cada processo.

Para o correto funcionamento do componente, o utilizador precisa estar conectado à internet com permissão de acesso ao BrFlow (https://www.brflow.com.br). Todas as requisições são realizadas usando o protocolo HTTPS.

As soluções aqui apresentadas não se propõe à tratar tecnicamente documentos que originalmente não estejam em condições adequadas de captura (estado de conservação, envolvidos sob invólucros plásticos, etc.).

Siga atentamente as instruções abaixo para a implementação bem-sucedida dos componentes.

### Pré requisitos

Os seguintes sistemas operacionais são suportados pelos componente:

- Android > 5 (21)

## As premissas para utilização dos componentes são:
- Para o uso dos componentes é necessário ter uma licença válida, gerada pela BrScan;
- Para a utilização dos componentes em modo mobile (smartphones e tablets) o aparelho deverá sempre estar no modo vertical ou horizontal;

## Inicialização do componente

O componente deverá ser inicializado através dos seguintes atributos

<b>chave</b>: string, obrigatório<br/>
<b>cropDocumento</b>: boolean, opcional, false<br/>
<b>validaDocumento</b>: boolean, opcional, false<br/>
<b>timeoutCapturaManual</b>: int, opcional, null<br/>

```java
Intent i = new Intent(DemoActivity.this, CapturaActivity.class);
i.putExtra("chave", "");
i.putExtra("cropDocumento", true);
i.putExtra("validaDocumento", true);
i.putExtra("timeoutCapturaManual", 10000);
startActivityForResult(i, RETORNO_CAPTURA_ACTIVITY);
```

## Retorno do componente

O retorno se dará atraves da call back

```java
onActivityResult(int requestCode, int resultCode, Intent data)
```

Aonde o resultCode poderá ser os seguintes

```java
CapturaActivity.SUCESSO_CAPTURA_IMAGEM
CapturaActivity.ERRO_CHAVE_INVALIDA
CapturaActivity.ERRO_USUARIO_CANCELOU
CapturaActivity.ERRO_CAPTURAR_IAMGEM
```

No caso de sucesso, os seguintes extras estarão disponíveis:

<b>arquivo</b>: string, contem o caminho da imagem gerada<br/>
<b>tipo</b>: string, opcional, tipo de documento encontrado pela validação do documento<br/>
<b>score</b>: string, opcional, valor de acurácia do documento<br/>

## Versionamento

Nós usamos [Github](https://github.com/) para versionar. Para as versões disponíveis, veja as [tags do repositório](https://github.com/brscan/web-brscansdk-demo). 
