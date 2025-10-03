# workshop-langchain4j

Workshop about GenAI-enabled applications powered with Langchain4j.

This workshop was first delivered as a conference talk at OpenSouthCode 2024. Also delivered as a talk at Commif Conf 2025, and in workshop format at OpenSlava 2025.

## Introduction and general instructions

The workshop is structured around several examples of growing complexity. Every example can be run as a simple Java program. To help with trying the integration with Ollama and different local models, there are abstract base classes and then a collection of ready-to-launch examples using the base classes with the different models. This also reinforces how effectively are models decoupled from Java code when using Langchain4j APIs.

The recommended approach is to follow the instructions in this ```README.md``` file, and rely on the solutions available in ```src/main/java``` folder in case of doubts or when stuck in a given step.

## Workshop pre-requisites

Pre-requisites are simple:

- Java 25
- Apache Maven 3.9.11
- Ollama 0.12.2 with any models you want to try
- And, of course, your favorite IDE

The code also works on Java 24 with preview features enabled, with any modern Maven 3.x version, and with any recent Ollama versions (I've been working with several Maven and Ollama versions in the last few months without impact on the source code).

Although out of scope for the current workshop content, the repo also has OpenAI and Vertex AI examples that you can try out. Take the following into consideration before running them:

- For OpenAI examples, you need to be a registered paid user. Provide a valid token in environment variable ```OPENAI_API_KEY```.

- For Vertex AI examples, you need to have a Google Cloud account with billing enabled and the necessary permissions to call the APIs. Provide a valid project id in environment variable ```VERTEXAI_PROJECT_ID``` and validate your credentials locally. This guide helped me to do it: [Authenticate to Vertex AI](https://cloud.google.com/vertex-ai/docs/authentication)

## Workshop agenda

This is the agenda for the workshop:

1. Introduction to Langchain4j and Ollama
2. A brief guide to model management with Ollama
3. Langchain4j "Hello World"
4. Managing context (a.k.a. chat memory)
5. Using local models for coding assistance
6. Implementing RAG (retrieval augmented generation)
7. Final words

## Workshop content

### 1. Introduction to Langchain4j and Ollama

I will eventually add the content directly here, but until then you may refer to my slides in previous events:

- [Slides in Spanish from Commit Conf 2025](https://speakerdeck.com/deors/langchain4j-y-ollama-integrando-llms-con-programas-java-at-commit-conf-2025)
- Slides in English from OpenSlava 2026 TBD

### 2. A brief guide to model management with Ollama

Once Ollama is installed, and depending on the chosen binary, you have four main use cases available:

- Use Ollama GUI to download, and use models in a "chatgpt-like" interface. Available as an app for MacOs, Linux and Windows here: [Ollama App Download](https://ollama.com/download)
- Use Ollama CLI to download, fine-tune, or create models.
- Use Ollama CLI to run and use a given model in a basic text console interface.
- Use Ollama CLI to run the Ollama server which exposes an API that our programs can use.

The second and fourth use cases are the ones that we will use in this workshop, although you are welcomed to experiment with the GUI and the text console as well.

Using Ollama GUI and CLI is quite straightforward, but I will list below the main CLI commands that we will use during this workshop:

- ```ollama pull <model_name:model_variant>```: Downloads a model from Ollama registry. You can search for models here: [Ollama Model Search](https://ollama.com/search)
- ```ollama run <model_name:model_variant>```: Runs the text console interface with the given model loaded.
- ```ollama serve```: Runs the Ollama server. No model is loaded by default, but they will be loaded as the requests are received.
- ```ollama list```: Lists the models downloaded and hence available for ```run``` and ```serve``` commands.
- ```ollama ps```: Lists the models running in the server.
- ```ollama rm <model_name:model_variant>```: Removes the model from the computer.

### 2.1. Selecting a model for the workshop

Ollama registry has a huge and growing collection of models available. This workshop has no limitations or coupling to specific models, so you are welcomed to try and use whichever you want. We strongly recommend to experiment and compare, as each model has its own strengths and limitations.

However, as this is an approach for local model inference, we have a strong limitation due to the limited resources available in our computers, basically:

- VRAM, that will limit the size of the models that can be loaded and run.
- CPU/GPU capabilities, that will limit what is practically doable (e.g., I've been able to run Qwen Image Edit locally but it took one hour to generate a 1024x1024 image so... can be done but not in a practical way).
- Disk space, as models might be quite large and if you are willing to experiment with multiple models and test the limit of your computer boundaries you can exhaust your available disk space quite fast.

Fortunately, small and middle-sized models in the 2-20 GB range are very capable and more than enough for experimentation, while you have the peace of mind that your solution can scale up to large models.

### 2.2. Model recommendations for small resource-constrained computers

Some recommendations for you if you have a computer with very limited resources (~8GB VRAM) or without a strong GPU:

- ```qwen3:4b```
- ```deepseek-r1:1.5b```
- ```nemotron-mini:4b```
- ```llama3.2:3b```
- ```gemma3:4b```
- ```phi3:3.8b```

### 2.3. Model recommendations for medium resource-limited computers

Some recommendations for you if you have a computer with limited resources (~16GB VRAM) and a dedicated GPU:

- ```qwen3:8b``` or ```qwen3:14b```
- ```deepseek-r1:8b``` or ```deepseek-r1:14b```
- ```mistral-nemo:12b```
- ```mistral:7b```
- ```llama3.1:8b```
- ```gemma3:12b```
- ```phi3:14b```

### 2.4. Model recommendations for more capable computers

Some recommendations for you if you have a capable computer (as per Today's standards, ~32GB VRAM) and a strong dedicated GPU (e.g., NVIDIA RTX or Apple Silicon):

- ```qwen3:30b```
- ```deepseek-r1:32b```
- ```gpt-oss:20b```
- ```mistral-small:22b```

It is also interesting to consider "coding-oriented" models to support in software engineering matters such as code understanding, code generation/refactoring or test code generation/refactoring:

- ```qwen3-coder:30b```
- ```devstral:24b```

