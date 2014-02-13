package org.checkerframework.framework.base;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;

import checkers.types.AnnotatedTypeMirror;

import org.checkerframework.framework.util.WrappedAnnotatedTypeMirror;

public class TreeAnnotatorAdapter<Q> extends checkers.types.TreeAnnotator {
    private TreeAnnotator<Q> underlying;
    private TypeMirrorConverter<Q> converter;

    public TreeAnnotatorAdapter(TreeAnnotator<Q> underlying,
            TypeMirrorConverter<Q> converter,
            QualifiedTypeFactoryAdapter<Q> factoryAdapter) {
        super(factoryAdapter);

        this.underlying = underlying;
        this.converter = converter;
    }

    TypeMirrorConverter<Q> getConverter() {
        return converter;
    }

    public Void visitBinary(BinaryTree node, AnnotatedTypeMirror atm) {
        QualifiedTypeMirror<Q> qtm = underlying.visitBinary(node,
                WrappedAnnotatedTypeMirror.wrap(atm));
        if (qtm != null) {
            converter.applyQualifiers(qtm, atm);
        } else {
            super.visitBinary(node, atm);
        }
        return null;
    }

    public Void visitLiteral(LiteralTree node, AnnotatedTypeMirror atm) {
        QualifiedTypeMirror<Q> qtm = underlying.visitLiteral(node,
                WrappedAnnotatedTypeMirror.wrap(atm));
        if (qtm != null) {
            converter.applyQualifiers(qtm, atm);
        } else {
            super.visitLiteral(node, atm);
        }
        return null;
    }
}
