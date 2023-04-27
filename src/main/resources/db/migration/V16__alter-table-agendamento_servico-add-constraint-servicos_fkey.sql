
ALTER TABLE
  "public"."agendamento_servico"
ADD
  CONSTRAINT "servicos_fkey" FOREIGN KEY ("servico") REFERENCES "public"."servico" ("id")