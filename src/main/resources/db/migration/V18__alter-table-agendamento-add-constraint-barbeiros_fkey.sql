
ALTER TABLE
  "public"."agendamento"
ADD
  CONSTRAINT "barbeiros_fkey" FOREIGN KEY ("barbeiro") REFERENCES "public"."barbeiro" ("id")