TGZNAME = kj479318-osrodek

.PHONY: all clean targz extract

clean:
	rm -f $(TGZNAME).tgz
	rm -rf $(TGZNAME)

targz:
	mkdir -p $(TGZNAME)
	cp -R src/* $(TGZNAME)
	tar -czf $(TGZNAME).tgz $(TGZNAME)
	rm -rf $(TGZNAME)

extract:
	tar -xzf $(TGZNAME).tgz