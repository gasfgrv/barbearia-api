
ALTER TABLE
  "public"."agendamento"
ADD
  CONSTRAINT "clientes_fkey" FOREIGN KEY ("cliente") REFERENCES "public"."cliente" ("id")