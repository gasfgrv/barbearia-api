CREATE TABLE "public"."agendamento" (
  "id" BIGINT NOT NULL,
  "cliente" BIGINT NOT NULL,
  "servicos" VARCHAR(255) NOT NULL,
  "barbeiro" BIGINT NOT NULL,
  "horario" TIMESTAMP NOT NULL,
  CONSTRAINT "agendamento_pkey" PRIMARY KEY ("id")
);